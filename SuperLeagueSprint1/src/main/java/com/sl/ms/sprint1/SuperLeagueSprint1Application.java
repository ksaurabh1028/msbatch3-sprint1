package com.sl.ms.sprint1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SuperLeagueSprint1Application {


    static Consumer<Inventory>  c1= p -> System.out.println(p);

    static Predicate<Inventory> p1 = p -> p.getDateUpload().equals(LocalDate.now());

    static Predicate<Inventory> p2 = p -> p.getDateUpload().getMonth().equals(LocalDate.now().getMonth());

    /**
     * 
     */
    static Consumer<Inventory>  c2=  (inventory -> {
      if (p1.test(inventory)) {
      System.out.println(inventory);
    }});

  public static void main(String[] args) {
    SpringApplication.run(SuperLeagueSprint1Application.class, args);

    String inputDir = "src\\main\\resources";  // Relative path taken - TODO - Dependency Injection to be done
    File[] files = new File(inputDir).listFiles(obj -> obj.isFile() && obj.getName().endsWith(".csv"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<Inventory> list = new ArrayList<>();
    List<Inventory> inventoryList = new ArrayList<>();

    for (File file : files) {

      System.out.println("Uploading file : " + file.getName());
      try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

        list = stream
            .skip(1)
            .map(line -> {
              String[] tempArray = line.split(",");
              return new Inventory(LocalDate.parse(tempArray[0], formatter),
                  tempArray[1],
                  tempArray[2],
                  Integer.parseInt(tempArray[3]),
                  Integer.parseInt(tempArray[4]));
        }).collect(Collectors.toCollection(ArrayList::new));

        inventoryList.addAll(list);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    stockSummaryPerDay(inventoryList);
    totalItemsSoldToday(inventoryList);
    totalItemsPerMonth(inventoryList);
    top5itemsLastMonth(inventoryList);
    summarySingleItem(inventoryList, "1");
  }

  /**
   * Stock Summary per day
  * @param inventoryList
  */
  private static void stockSummaryPerDay(List<Inventory> inventoryList) {

    System.out.println("Stock Summary per day:");
    inventoryList.forEach(c1);
  }

  /**
   * Stock Summary sold today
  * @param inventoryList
  */
  private static void totalItemsSoldToday(List<Inventory> inventoryList) {
    System.out.println("Stock Summary sold today:");
    inventoryList.forEach(c2);
  }

  /**
   * This method provides summary for a single item
 * @param inventoryList
 * @param itemId
 */
  private static void summarySingleItem(List<Inventory> inventoryList, String itemId) {
    System.out.println("Summary of Single item with id:" + itemId);

    inventoryList.stream()
      .filter(p -> p.getId().equals(itemId))
      .forEach(System.out::println);
  }

  /**
   * Top 5 items last month
 * @param inventoryList
 */
  private static void top5itemsLastMonth(List<Inventory> inventoryList) {

    System.out.println("Top 5 items last month");

    inventoryList.stream()
      .filter(p2)
      .collect(Collectors.groupingBy(Inventory::getName, LinkedHashMap::new,Collectors.summingInt(Inventory::getQuantity)))
      .entrySet()
      .stream()
      .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
      .limit(5)
      .forEach(System.out::println);
  }

/**
 * Total items per month
* @param inventoryList
*/
  private static void totalItemsPerMonth(List<Inventory> inventoryList) {
    System.out.println("Total items per month\n");
    inventoryList.stream()
    .collect(Collectors.groupingBy(Inventory::getDateUploadMonth, TreeMap::new,Collectors.summingInt(Inventory::getQuantity)))
    .entrySet()
    .stream()
    .forEach(System.out::println);
  }

}
