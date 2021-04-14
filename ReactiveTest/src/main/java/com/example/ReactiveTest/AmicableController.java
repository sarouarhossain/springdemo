package com.example.ReactiveTest;

import com.example.ReactiveTest.models.Amicable;
import com.example.ReactiveTest.services.AmicableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/amicable")
public class AmicableController {

  private final AmicableService amicableService;

  AmicableController(AmicableService amicableService) {
    this.amicableService = amicableService;
  }

  @GetMapping("/cf/{limit}")
  public CompletionStage<List<Amicable>> getAmicableCf(@PathVariable Integer limit) {
    return amicableService.getAmicableList(limit);
  }

  @GetMapping("/reactive/{limit}")
  public Flux<List<Object>> getAmicableReactive(@PathVariable String limit) {
    return null;
  }
}
