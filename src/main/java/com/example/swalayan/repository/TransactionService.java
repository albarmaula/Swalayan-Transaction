package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import com.example.swalayan.model.Product;
import com.example.swalayan.model.Transaction;
import com.example.swalayan.model.TransactionItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private static final String EMPLOYEE_SERVICE_URL = "http://localhost:8090/api/employee/{nip}";
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8091/product/{idProduct}";

    private final TransactionRepository transactionRepository;
    private final WebClient webClient;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, WebClient webClient) {
        this.transactionRepository = transactionRepository;
        this.webClient = webClient;
    }

    @Transactional
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        // Retrieve employee details from the employee service

            Employee employee = retrieveEmployee(transaction.getNip());
            if (employee != null) {
                // Set the retrieved employee ID
                employee.setNip(employee.getNip());
            }

        // Retrieve product details from the product service for each transaction item
        for (TransactionItem item : transaction.getTransactionItems()) {
            Product product = retrieveProduct(item.getId_product());
            if (product != null) {
                // Set the retrieved product ID
                item.setId_product(product.getId_product());
                double subTotal = product.getPrice() * item.getQuantity();
                item.setSub_total(subTotal);
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        transaction.setYear(currentDateTime.getYear());
        transaction.setMonth(currentDateTime.getMonthValue());
        transaction.setDay(currentDateTime.getDayOfMonth());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        transaction.setTime(LocalTime.now().format(dtf));

        double totalAmount = transaction.getTransactionItems().stream()
                .mapToDouble(TransactionItem::getSub_total)
                .sum();
        transaction.setTotal_amount(totalAmount);

        // Save the modified transaction with updated employee and product IDs
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public List<Transaction> findTransactionsByYear(int year) {
        return transactionRepository.findByYear(year);
    }

    @Transactional
    public List<Transaction> findTransactionByYearAndMonth(int year, int month) {
        return transactionRepository.findByYearAndMonth(year, month);
    }

    @Transactional
    public List<Transaction> findTransactionByYearAndMonthAndDay(int year, int month, int day) {
        return transactionRepository.findByYearAndMonthAndDay(year, month, day);
    }

    @Transactional
    public List<Transaction> findTransactionsByNip(Long nip) {
        return transactionRepository.findByNip(nip);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    private Employee retrieveEmployee(Long nip) {
        // Make REST API call to employee service to retrieve employee details
        return webClient.method(HttpMethod.GET)
                .uri(EMPLOYEE_SERVICE_URL, nip)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }

    private Product retrieveProduct(Long idProduct) {
        // Make REST API call to product service to retrieve product details
        return webClient.method(HttpMethod.GET)
                .uri(PRODUCT_SERVICE_URL, idProduct)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}

    ////////////////////////////model mapper/////////////////////////////////////////////////
//    private TransactionDTO convertToDTO(Transaction transaction) {
//        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
//        transactionDTO.setNip(transaction.getNip().getNip());
//        List<TransactionItemDTO> transactionItemDTOs = transaction.getTransactionItems().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        transactionDTO.setTransactionItems(transactionItemDTOs);
//
//        return transactionDTO;
//    }
//
//    private TransactionItemDTO convertToDTO(TransactionItem transactionItem) {
////        return modelMapper.map(transactionItem, TransactionItemDTO.class);
//
//        TransactionItemDTO transactionItemDTO = modelMapper.map(transactionItem, TransactionItemDTO.class);
//
//        return transactionItemDTO;
//    }
//
//    private Transaction convertToEntity(TransactionDTO transactionDTO) {
//        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
//
//        Employee cashier = new Employee();
//        cashier.setNip(transactionDTO.getNip());
////        Employee cashier = getEmployeeById(transactionDTO.getNip());
//
//        transaction.setNip(cashier);
//        List<TransactionItem> transactionItems = transactionDTO.getTransactionItems().stream()
//                .map(this::convertToEntity)
//                .collect(Collectors.toList());
//        transaction.setTransactionItems(transactionItems);
//        return transaction;
//    }
//
//    private TransactionItem convertToEntity(TransactionItemDTO transactionItemDTO) {
//        TransactionItem transactionItem = modelMapper.map(transactionItemDTO, TransactionItem.class);
//
////        Product product = new Product();
////        product.setId_product(transactionItemDTO.getId_product());
//        Product product = getProductById(transactionItemDTO.getId_product());
//
//        transactionItem.setId_product(product);
//        return transactionItem;
//    }

    //////////////////////////////Rest Tamplate/////////////////////////////////////////////////
//    public Employee getEmployeeById(Long id) {
//        String employeeUrl = "http://localhost:8090/api/employee" + id;
//        ResponseEntity<Employee> response = restTemplate.exchange(employeeUrl, HttpMethod.GET, null, Employee.class);
//        return response.getBody();
//    }
//
//    public Product getProductById(Long id) {
//        String productUrl = "http://PRODUCT-SERVICE/products/" + id;
//        ResponseEntity<Product> response = restTemplate.exchange(productUrl, HttpMethod.GET, null, Product.class);
//        return response.getBody();
//    }

///////////////////////dump//////////////
//    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
//        Transaction transaction = convertToEntity(transactionDTO);
//
//        Employee employee = fetchEmployee(transaction.getNip());
//        if (employee != null) {
//            // Set employee details in the transaction
//            transaction.setNip(employee);
//        }
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        transaction.setYear(currentDateTime.getYear());
//        transaction.setMonth(currentDateTime.getMonthValue());
//        transaction.setDay(currentDateTime.getDayOfMonth());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//        transaction.setTime(LocalTime.now().format(dtf));
//
//        // Calculate subTotal for each transaction item
////        for (TransactionItem transactionItem : transaction.getTransactionItems()) {
////            Product product = getProductById(transactionItem.getId_product().getId_product());
////            double subTotal = product.getPrice() * transactionItem.getQuantity();
////            transactionItem.setSub_total(subTotal);
////        }
//        // Calculate totalAmount for the transaction
//        double totalAmount = transaction.getTransactionItems().stream()
//                .mapToDouble(TransactionItem::getSub_total)
//                .sum();
//        transaction.setTotal_amount(totalAmount);
//
//        Transaction createdTransaction = transactionRepository.save(transaction);
//        return convertToDTO(createdTransaction);
//    }
//
//    public TransactionService(TransactionRepository transactionRepository, RestTemplate restTemplate) {
//        this.transactionRepository = transactionRepository;
//        this.restTemplate = restTemplate;
//    }

//    @Transactional
//    public List<TransactionDTO> findTransactionsByDay(int day) {
//        List<Transaction> transactions = transactionRepository.findByDay(day);
//        return transactions.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public List<TransactionDTO> findTransactionsByMonth(int month) {
//        List<Transaction> transactions = transactionRepository.findByMonth(month);
//        return transactions.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

//    @Transactional
//    public TransactionDTO updatetransaction(TransactionDTO transactionDTO){
//        Transaction existingtransaction = transactionRepository.findById(transactionDTO.getId_trans()).get();
//        existingtransaction.setId_trans(transactionDTO.getId_trans());
//        existingtransaction.setYear(transactionDTO.getYear());
//        existingtransaction.setMonth(transactionDTO.getMonth());
//        existingtransaction.setDay(transactionDTO.getDay());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//        existingtransaction.setTime(transactionDTO.getTime());
//        double totalAmount = existingtransaction.getTransactionItems().stream()
//                .mapToDouble(TransactionItem::getSub_total)
//                .sum();
//        existingtransaction.setTotal_amount(totalAmount);
//        Transaction updatetransaction = transactionRepository.save(existingtransaction);
//        return convertToDTO(updatetransaction);
//    }