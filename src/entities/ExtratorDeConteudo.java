package entities;

import entities.Conteudo;

import java.util.List;

public interface ExtratorDeConteudo {

    List<Conteudo> extraiConteudos(String json);

}