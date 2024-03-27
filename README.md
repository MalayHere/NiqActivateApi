
# eCommerce Assignment

API service that provides shoppers
personalized information to eCommerce servers.

swagger documentation link-
http://localhost:8081/swagger-ui/index.html#/



## API Reference

#### Get all items

```http
  GET /products/{shopperId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `shopperId ` | `string` | **Required**. Your API key |  
| `category ` | `string` |  |  
| `brand ` | `string` |  |  
| `limit ` | `integer` | **Default value** 10 |  


#### Add product meta data

```http
  POST /products/add
```

Request body
Schema
[ProductMetadata{
productId*	[...]
category*	[...]
brand*	[...]
}]

#### Add shopper's personalised data

```http
  POST /personalized-products/add
```

Request body
Schema
PersonalizedProductList{
shopperId*	[...]
shelfItemList	[...]
}



## Screenshots
DataBase Design


![Untitled](https://github.com/MalayHere/NiqActivateApi/assets/158697312/87f3b415-5f76-475f-ae24-a6f05fec3860)


