package com.example.parser;

import com.example.parser.dto.AmsroDto;
import com.example.parser.dto.AmsrosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ParserApplication implements CommandLineRunner {

	private final FedresursClient client;

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}

	@Override
	public void run(String... args) {
		int limit = 100;
		int offset = 0;

		AmsrosResponse first = client.loadPage(limit, offset);
		int total = first.getTotal();

		List<AmsroDto> all = new ArrayList<>(first.getPageData());

		for (offset = limit; offset < total; offset += limit) {
			AmsrosResponse page = client.loadPage(limit, offset);
			all.addAll(page.getPageData());
		}

		System.out.println("TOTAL = " + total);
		System.out.println("ALL SIZE = " + all.size());
		System.out.println("FIRST NAME = " + all.get(0).getName());
	}

}
