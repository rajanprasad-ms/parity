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
import com.microsoft.itchserver.ticker.MarketData;

import java.io.IOException;

import static org.jvirtanen.util.Applications.error;


class ItchMarketDataProcessor implements ITCH50Listener {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final MarketData marketData;


    ItchMarketDataProcessor(MarketData marketData) {
        this.marketData = marketData;
    }

    @Override
    public void systemEvent(ITCH50.SystemEvent message) throws IOException {
        System.out.println("SystemEvent : " + mapper.writeValueAsString(message));
        marketData.send(message);
    }

    @Override
    public void stockDirectory(ITCH50.StockDirectory message) throws IOException {
        System.out.println("StockDirectory : " + mapper.writeValueAsString(message));
        marketData.send(message);
    }

    @Override
    public void stockTradingAction(ITCH50.StockTradingAction message) throws IOException {
        System.out.println("StockTradingAction : " + mapper.writeValueAsString(message));
        marketData.send(message);
    }

    @Override
    public void regSHORestriction(ITCH50.RegSHORestriction message) throws IOException {
        System.out.println("RegSHORestriction : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void marketParticipantPosition(ITCH50.MarketParticipantPosition message) throws IOException {
        System.out.println("MarketParticipantPosition : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void mwcbDeclineLevel(ITCH50.MWCBDeclineLevel message) throws IOException {
        System.out.println("MWCBDeclineLevel : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void mwcbStatus(ITCH50.MWCBStatus message) throws IOException {
        System.out.println("MWCBStatus : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void ipoQuotingPeriodUpdate(ITCH50.IPOQuotingPeriodUpdate message) throws IOException {
        System.out.println("IPOQuotingPeriodUpdate : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void luldAuctionCollar(ITCH50.LULDAuctionCollar message) throws IOException {
        System.out.println("LULDAuctionCollar : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void operationalHalt(ITCH50.OperationalHalt message) throws IOException {
        System.out.println("OperationalHalt : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void addOrder(ITCH50.AddOrder message) throws IOException {
        System.out.println("AddOrder : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void addOrderMPID(ITCH50.AddOrderMPID message) throws IOException {
        System.out.println("AddOrderMPID : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderExecuted(ITCH50.OrderExecuted message) throws IOException {
        System.out.println("OrderExecuted : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderExecutedWithPrice(ITCH50.OrderExecutedWithPrice message) throws IOException {
        System.out.println("OrderExecutedWithPrice : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderCancel(ITCH50.OrderCancel message) throws IOException {
        System.out.println("OrderCancel : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderDelete(ITCH50.OrderDelete message) throws IOException {
        System.out.println("OrderDelete : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void orderReplace(ITCH50.OrderReplace message) throws IOException {
        System.out.println("OrderReplace : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void trade(ITCH50.Trade message) throws IOException {
        System.out.println("Trade : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void crossTrade(ITCH50.CrossTrade message) throws IOException {
        System.out.println("CrossTrade : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void brokenTrade(ITCH50.BrokenTrade message) throws IOException {
        System.out.println("BrokenTrade : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void noii(ITCH50.NOII message) throws IOException {
        System.out.println("NOII : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }

    @Override
    public void rpii(ITCH50.RPII message) throws IOException {
        System.out.println("RPII : " + mapper.writeValueAsString(message));
        marketData.send(message);

    }
}
