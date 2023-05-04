package com.kakao.jPanda.jst.handler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.jPanda.jst.domain.TalentDto;
import com.kakao.jPanda.jst.domain.TradeDto;
import com.kakao.jPanda.jst.service.TradeService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class TradeWebSocketHandler extends TextWebSocketHandler{
	
	private final TradeService tradeService;
	
	@Autowired
	public TradeWebSocketHandler(TradeService tradeService) {
		this.tradeService = tradeService;
	}
	
	//Session 관리를 위한 Map
	private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
	
	//ObjectWrapper
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String memberId = (String) session.getAttributes().get("memberId");
		log.info("afterConnectionEstablished memberId : " +  memberId);
		if (memberId != null) {
			sessionMap.put(memberId, session);
		}
		
		sessionMap.forEach((key, value)->{
			log.info("session map>> id : {} session : {}", key, value);
		});
		
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		List<TradeDto> tradeDtoList = objectMapper.readValue((String)message.getPayload(), new TypeReference<List<TradeDto>>() {});
		
		Map<String, List<TradeDto>> groupedTradeDtoMap = tradeDtoList.stream().collect(Collectors.groupingBy(TradeDto::getMemberId));
		groupedTradeDtoMap.forEach((memberId, trades) -> {
			WebSocketSession receiverSession = sessionMap.get(memberId);
			
			if (receiverSession != null) {
				try {
					receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(trades)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed removed memberId : {} session : {}", (String) session.getAttributes().get("memberId"),sessionMap.values().remove(session));
		sessionMap.values().remove(session);
		
	}
	
	public TradeDto findTradeDto(TradeDto tradeDto) {
		if (tradeDto.getTalentNo() != null) {
			TradeDto resultTradeDto = (TradeDto)tradeService.findTalentByTalentNo(tradeDto.getTalentNo());
	        return resultTradeDto;
	        
		} else if(tradeDto.getExchangeNo() != null) {
			return tradeService.findExchangeByExchangeNo(tradeDto.getExchangeNo());
			
		} else if(tradeDto.getRefundPurchaseNo() != null) {
			return tradeService.findRefundByRefundPurchaseNo(tradeDto.getRefundPurchaseNo());
			
		} else {
			return null;
		}
		
	}
	
	
	
}//end class