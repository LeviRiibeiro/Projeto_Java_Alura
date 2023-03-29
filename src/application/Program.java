package application;

import entities.GeradoraDeFigurinhas;
import entities.JsonParse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
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

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();


        }



    }
}