/*
 * Copyright 2014 Parity authors
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
package com.microsoft.itchserver.ticker;

import static org.jvirtanen.util.Applications.*;

import com.paritytrading.nassau.moldudp64.*;
import com.microsoft.itchserver.net.itch.ITCH50;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Logger;

public class MarketData {

    private static final Logger Log = Logger.getLogger(MarketData.class.getName());

    private final MoldUDP64Server transport;

    private final MoldUDP64RequestServer requestTransport;

    private final MoldUDP64DefaultMessageStore messages;

    private MoldUDP64DownstreamPacket packet;

    private final ByteBuffer buffer;

    private MarketData(MoldUDP64Server transport, MoldUDP64RequestServer requestTransport) {
        this.transport = transport;

        this.requestTransport = requestTransport;

        this.messages = new MoldUDP64DefaultMessageStore();

        this.packet = new MoldUDP64DownstreamPacket();
        this.buffer = ByteBuffer.allocateDirect(1024);
    }

    static MarketData open(String session, NetworkInterface multicastInterface,
                           InetSocketAddress multicastGroup,
                           InetSocketAddress requestAddress) throws IOException {
        DatagramChannel channel = DatagramChannel.open(StandardProtocolFamily.INET);

        channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, multicastInterface);
        channel.connect(multicastGroup);

        MoldUDP64Server transport = new MoldUDP64Server(channel, session);

        DatagramChannel requestChannel = DatagramChannel.open();

        requestChannel.bind(requestAddress);
        requestChannel.configureBlocking(false);

        MoldUDP64RequestServer requestTransport = new MoldUDP64RequestServer(requestChannel);

        return new MarketData(transport, requestTransport);
    }

    MoldUDP64Server getTransport() {
        return transport;
    }

    MoldUDP64RequestServer getRequestTransport() {
        return requestTransport;
    }

    void serve() {
        try {
            requestTransport.serve(messages);
        } catch (IOException e) {
            fatal(e);
        }
    }

    private long timestamp() {
        return (System.currentTimeMillis() - StockTicker.EPOCH_MILLIS) * 1_000_000;
    }

    public void send(ITCH50.Message message) {
        buffer.clear();
        message.put(buffer);
        buffer.flip();

        if(packet.remaining() < buffer.remaining()){
            Log.finer("Messages packed : " + packet.messageCount());
            try{
                transport.send(packet);
                packet.payload().flip();

                // to fix OOM.
                //                messages.put(packet);
            } catch (IOException e) {
                fatal(e);
            }
            packet = new MoldUDP64DownstreamPacket();
        }

        try {
            packet.put(buffer);
        } catch (final MoldUDP64Exception ex) {
//            Log.throwing(this.getClass().getName(), "send", ex);
//            Log.info( "messages in last packet = " + packet.messageCount());
//            Log.info( "exception = " + ex.getMessage());
//            ex.printStackTrace();
            Log.info("new packet remaining = " + packet.remaining()
                    + " ... buffer remaining = " + buffer.remaining());
            fatal(ex);
        }
    }

}