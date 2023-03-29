package application;

import entities.JsonParse;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {


        // fazer a conexao http e buscar o top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String corpo = response.body();
        System.out.println(corpo);

        // Pegar só os dados que interessam (Titulo, Poster, classificacao)

        JsonParse js = new JsonParse();
        List<Map<String, String>> listaDeFilmes = js.parse(corpo);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
        }



    }
}