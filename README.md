
# eCommerce Assignment

API service that provides shoppers
personalized information to eCommerce servers.



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

![Untitled](https://github.com/MalayHere/NiqActivateApi/assets/158697312/a92f0176-f2d9-4503-84fa-aa928ae0cb88)


