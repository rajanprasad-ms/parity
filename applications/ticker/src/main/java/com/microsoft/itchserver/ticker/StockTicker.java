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

import static java.util.Arrays.*;
import static org.jvirtanen.util.Applications.*;

import com.paritytrading.nassau.util.BinaryFILE;
import com.microsoft.itchserver.net.itch.ITCH50Parser;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import org.jvirtanen.config.Configs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.ZoneId;

public class StockTicker {

    public static void main(String[] args) {
        if (args.length < 1)
            usage();

        boolean taq = args[0].equals("-t");

        try {
            main(taq, taq ? copyOfRange(args, 1, args.length) : args);
        } catch (ConfigException | FileNotFoundException e) {
            error(e);
        } catch (IOException e) {
            fatal(e);
        }
    }

    private static void main(boolean taq, String[] args) throws IOException {
        if (args.length == 2) {
            read(taq, config(args[0]), new File(args[1]));
            return;
        }
        usage();
        return;
    }

    private static void read(boolean taq, Config config, File file) throws IOException {
        final String protocol = config.getString("market-data.protocol");
        System.out.println(protocol);
        if("itch50".equals(protocol)){
            ItchMarketDataProcessor processor = new ItchMarketDataProcessor(marketData(config));
            BinaryFILE.read(file, new ITCH50Parser(processor));

        }
    }

    private static void usage() {
        System.err.println("Usage: parity-ticker [-t] <configuration-file> [<input-file>]");
        System.exit(2);
    }

    static final long EPOCH_MILLIS = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
            .toInstant().toEpochMilli();


    public static MarketData marketData(Config config) throws IOException {
        String           session            = config.getString("market-data.session");
        NetworkInterface multicastInterface = Configs.getNetworkInterface(config, "market-data.multicast-interface");
        InetAddress multicastGroup     = Configs.getInetAddress(config, "market-data.multicast-group");
        int              multicastPort      = Configs.getPort(config, "market-data.multicast-port");
        InetAddress      requestAddress     = Configs.getInetAddress(config, "market-data.request-address");
        int              requestPort        = Configs.getPort(config, "market-data.request-port");

        return MarketData.open(session, multicastInterface,
                new InetSocketAddress(multicastGroup, multicastPort),
                new InetSocketAddress(requestAddress, requestPort));
    }

}