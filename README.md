# Sales-API

API para cadastro de vendedores e vendas, realizado para o processo de seleção da XBrain.


## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/)
- [H2 Database](https://www.h2database.com/html/main.html)

## Práticas
- Padrão MVC
- API Rest
- Injeção de Dependências
- Validação
- Tratamento de exceções
- Documentação automática pelo Swagger (da OpenAPI 3)



## API Endpoints


- Lista completa de vendedores
```
  GET localhost:8080/seller
```

- Buscar vendedor por ID
```
  GET localhost:8080/seller/{id}
```

- Lista completa de vendas
```
  GET localhost:8080/sale
```
- Buscar venda por ID
```

  GET localhost:8080/sale/{id}
```

- Criar Vendedor
```
  POST localhost:8080/seller

Body:
{
  "name": "Complete Seller Name",
  "email": "sellerEmail@email.com"
}
```

- Criar Venda
```
  POST localhost:8080/sale

Body:
{
  "saleDate": "2024-02-12",
  "totalCost": 37.5,
  "sellerId": "75e59327-f013-46ba-bc51-88a64fbf63e9"
}
```

- Criar ranking de vendedores por vendas em um período de tempo
```
  GET localhost:8080/seller/ranking?start=2024-02-10&end=2024-02-15

Datas no formato: "2024-02-10" (YYYY-MM-DD)
```


