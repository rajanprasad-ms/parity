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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.itchserver.net.itch.ITCH50;
import com.microsoft.itchserver.net.itch.ITCH50Listener;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jvirtanen.util.Applications.error;


class ItchMarketDataProcessor implements ITCH50Listener {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final MarketData marketData;
    private static final Logger Log = Logger.getLogger(ItchMarketDataProcessor.class.getName());


    ItchMarketDataProcessor(MarketData marketData) {
        this.marketData = marketData;
    }

    @Override
    public void systemEvent(ITCH50.SystemEvent message) throws IOException {
        Log.fine( "SystemEvent : " + mapper.writeValueAsString(message));
        marketData.send(message);
    }

    @Override
    public void stockDirectory(ITCH50.StockDirectory message) throws IOException {
        Log.fine( "StockDirectory : " + mapper.writeValueAsString(message));
        marketData.send(message);
    }

    @Override
    public void stockTradingAction(ITCH50.StockTradingAction message) throws IOException {
        Log.fine( "StockTradingAction : " + mapper.writeValueAsString(message));
        marketData.send(message);
    }

    @Override
    public void regSHORestriction(ITCH50.RegSHORestriction message) throws IOException {
        Log.fine( "RegSHORestriction : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void marketParticipantPosition(ITCH50.MarketParticipantPosition message) throws IOException {
        Log.fine( "MarketParticipantPosition : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void mwcbDeclineLevel(ITCH50.MWCBDeclineLevel message) throws IOException {
        Log.fine( "MWCBDeclineLevel : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void mwcbStatus(ITCH50.MWCBStatus message) throws IOException {
        Log.fine( "MWCBStatus : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void ipoQuotingPeriodUpdate(ITCH50.IPOQuotingPeriodUpdate message) throws IOException {
        Log.fine( "IPOQuotingPeriodUpdate : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void luldAuctionCollar(ITCH50.LULDAuctionCollar message) throws IOException {
        Log.fine( "LULDAuctionCollar : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void operationalHalt(ITCH50.OperationalHalt message) throws IOException {
        Log.fine( "OperationalHalt : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void addOrder(ITCH50.AddOrder message) throws IOException {
        Log.fine( "AddOrder : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void addOrderMPID(ITCH50.AddOrderMPID message) throws IOException {
        Log.fine( "AddOrderMPID : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderExecuted(ITCH50.OrderExecuted message) throws IOException {
        Log.fine( "OrderExecuted : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderExecutedWithPrice(ITCH50.OrderExecutedWithPrice message) throws IOException {
        Log.fine( "OrderExecutedWithPrice : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderCancel(ITCH50.OrderCancel message) throws IOException {
        Log.fine( "OrderCancel : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderDelete(ITCH50.OrderDelete message) throws IOException {
        Log.fine( "OrderDelete : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderReplace(ITCH50.OrderReplace message) throws IOException {
        Log.fine( "OrderReplace : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void trade(ITCH50.Trade message) throws IOException {
        Log.fine( "Trade : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void crossTrade(ITCH50.CrossTrade message) throws IOException {
        Log.fine( "CrossTrade : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void brokenTrade(ITCH50.BrokenTrade message) throws IOException {
        Log.fine( "BrokenTrade : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void noii(ITCH50.NOII message) throws IOException {
        Log.fine( "NOII : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void rpii(ITCH50.RPII message) throws IOException {
        Log.fine( "RPII : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }
}
