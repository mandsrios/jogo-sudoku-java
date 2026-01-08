package br.com.project.model;

public enum GameStatusEnum {
    NAOINICIADO("Não iniciado!"),
    INCOMPLETO("Incompleto!"),
    COMPLETO("Completo!");

    private String label;

    GameStatusEnum(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
