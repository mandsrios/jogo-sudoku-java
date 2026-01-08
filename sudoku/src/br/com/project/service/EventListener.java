package br.com.project.service;

import jdk.jfr.Event;

public interface EventListener {

    void alterar(final EventEnum eventType);
}
