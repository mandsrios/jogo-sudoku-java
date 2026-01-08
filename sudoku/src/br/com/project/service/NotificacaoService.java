package br.com.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.project.service.EventEnum.LIMPAR_ESPACO;


public class NotificacaoService {

    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
        put(LIMPAR_ESPACO, new ArrayList<>());
    }};

    public void subscrever(final EventEnum eventType, EventListener listener){
        var selectedListener = listeners.get(eventType);
        selectedListener.add(listener);
    }

    public void notify(final EventEnum eventType){
        listeners.get(eventType).forEach(l -> l.alterar(eventType));
    }
}
