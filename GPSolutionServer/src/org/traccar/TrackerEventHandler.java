/*
 * Copyright 2012 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar;

import java.util.List;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.traccar.helper.Log;
import org.traccar.model.DataManager;
import org.traccar.model.Position;

/**
 * Tracker message handler
 */
@ChannelHandler.Sharable
public class TrackerEventHandler extends IdleStateAwareChannelHandler {

    /**
     * Data manager
     */
    private DataManager dataManager;

    // Pallavi: later Change to default
    public TrackerEventHandler(DataManager newDataManager) {
        super();
        dataManager = newDataManager;
    }

    public void processSinglePosition(Position position) {
    	System.out.println("Started processSinglePosition");
        if (position == null) {
            Log.info("processSinglePosition null message");
        } else {
            StringBuilder s = new StringBuilder();
            s.append("device: ").append(position.getDeviceId()).append(", ");
            s.append("time: ").append(position.getTime()).append(", ");
            s.append("lat: ").append(position.getLatitude()).append(", ");
            s.append("lon: ").append(position.getLongitude());
            Log.info(s.toString());
            System.out.println("Position Object "+s.toString());
        }

        // Write position to database
        try {
        	System.out.println("Getting db manager");
            Long id = dataManager.addPosition(position);
            if (id != null) {
            	System.out.println("updating latest position in db");
                dataManager.updateLatestPosition(position.getDeviceId(), id);
            }
        } catch (Exception error) {
        	System.out.println("Exception occured while updating position in DB: "+error.getMessage());
            Log.warning(error);
        }
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    	System.out.println("Started messageReceived");
        if (e.getMessage() instanceof Position) {
        	System.out.println("Instance of position");
            processSinglePosition((Position) e.getMessage());
        } else if (e.getMessage() instanceof List) {
        	System.out.println("Instance of list");
            List<Position> positions = (List<Position>) e.getMessage();
            for (Position position : positions) {
                processSinglePosition(position);
            }
        }
        System.out.println("Ended messageReceived");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Log.info("Closing connection by disconnect");
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        Log.info("Closing connection by exception");
        e.getChannel().close();
    }

    @Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) {
        Log.info("Closing connection by timeout");
        e.getChannel().close();
    }

}
