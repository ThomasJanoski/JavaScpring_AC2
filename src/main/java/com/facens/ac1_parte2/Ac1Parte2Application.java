package com.facens.ac1_parte2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.facens.ac1_parte2.Entities.Diretor;
import com.facens.ac1_parte2.Entities.Filme;
import com.facens.ac1_parte2.Repositories.DiretorRepository;
import com.facens.ac1_parte2.Repositories.FilmeRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Ac1Parte2Application {
	private final DiretorRepository diretorRepository;
	private final FilmeRepository filmeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ac1Parte2Application.class, args);
	}

	@Bean
	protected CommandLineRunner run() {
		return args -> {
			Diretor diretor_a = Diretor.builder()
					.nome("Steven Spielberg")
					.build();

			Diretor diretor_b = Diretor.builder()
					.nome("Francis Ford Coppola")
					.build();

			Filme filme_a = Filme.builder()
					.titulo("O Poderoso Chefão")
					.duracao(175)
					.diretor(diretor_a)
					.build();
			Filme filme_b = Filme.builder()
					.titulo("Um Sonho de Liberdade")
					.duracao(142)
					.diretor(diretor_b)
					.build();
			Filme filme_c = Filme.builder()
					.titulo("A Lista de Schindler")
					.duracao(195)
					.diretor(diretor_b)
					.build();

			diretorRepository.saveAll(List.of(diretor_a, diretor_b));
			filmeRepository.saveAll(List.of(filme_a, filme_b, filme_c));

			System.out.println("\nTotal Filmes");
			List<Filme> Listafilmes = filmeRepository.findAll();
			Listafilmes.forEach(System.out::println);

			System.out.println("\nFrancis Ford Coppola e seus filmes:");
			Diretor diretor = diretorRepository.findDiretorByIdWithFilmes(diretor_b.getId().intValue());
			diretor.getFilmes().forEach(System.out::println);

			System.out.println("\nFilmes com duração maior que 150 minutos:");
			List<Filme> filmesLongos = filmeRepository.findByDuracaoGreaterThan(150);
			filmesLongos.forEach(System.out::println);

			System.out.println("\nFilmes com duração menor que 150 minutos:");
			List<Filme> filmesCurtos = filmeRepository.findByDuracaoLessThanEqual(150);
			filmesCurtos.forEach(System.out::println);

			System.out.println("\nFilmes com título começando com 'A':");
			List<Filme> filmesComA = filmeRepository.findByTituloStartingWith("A");
			filmesComA.forEach(System.out::println);

			System.out.println("\nDiretores com nome começando com 'Steven':");
			List<Diretor> diretoresNome = diretorRepository.findByNomeStartingWith("Steven");
			diretoresNome.forEach(System.out::println);
		};
	}
}
