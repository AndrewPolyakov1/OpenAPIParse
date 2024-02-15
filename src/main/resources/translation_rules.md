# Правила перевода

## Файл API

Базовая структура объекта (только `Required` и основные поля)

```
openapi: String
info: Info Object
    Info Object:
        title: String
        version: String
servers: List<Server Object>
    Server Object:
        url: String
path: Path Item Object
    Path Item Object:
        $ref: String 
        put	    : Operation Object	
        post	: Operation Object
        delete	: Operation Object
        options	: Operation Object
        head	: Operation Object
        patch	: Operation Object
        trace   : Operation Object
```

## File outline

```
library <title>;
version

```
либо добавить ключевое слово для api
```
api <title>;
```

### Server

```
automaton <Server>{
    state Constructed;
    Constructed -> self (url);
}
```

### Request

Проблема: 
Структура `get...` для разных `path` разная

```
automaton <Request>{
    state Constructed;
    Constructed -> self (get);
    Constructed -> self (post);
    ...
    Constructed -> self (post);
}

fun Requests.get(url: URL, headers: Dict): Response {
    static "requests";
}
...

automaton Response {
    state Constructed;
    shift Constructed -> self (status_code);
    shift Constructed -> self (response_body);
}


type <path>#200 {
    
}
```

## Типы

Описать стандартные типы OpenAPI.
Базовые типы OpenAPI:


| type	    | format   | 	Comments                       |
|----------|----------|---------------------------------|
| integer	 | int32	   | signed 32 bits                  |
| integer	 | int64	   | signed 64 bits (a.k.a long)     |
| number	  | float    |                                 |
| number	  | double   |                                 |
| string		 | password | A hint to UIs to obscure input. |

```
types {
    string();
    number();
    integer();
    boolean();
    array();
    object();
}
```

Для типа Object:

Стандартные объекты OpenAPI:

- OpenAPI
- Info
- Contact
- License
- Server
- Paths
- Path Item
- Parameter
- RequestBody
- 


Предлагаю добавить дополнительно типы `url` и `PathObject` так как на них идут некоторые ограничения, которые стоит учитывать. 
Возможно добавить тип `HumanReadable` для описания (но не обязательно).
## \<Path item/operation/request body\> object

Содержит либо  `Request Body Object` либо ` Reference Object`

Операции:
- put	    : Operation Object	
- post	: Operation Object
- delete	: Operation Object
- options	: Operation Object
- head	: Operation Object
- patch	: Operation Object
- trace   : Operation Object
## Объект запроса

**Формат:**

```yaml
get:
    tags:
      - pet
    summary: Updates a pet in the store with form data
    operationId: updatePetWithForm
    parameters:
      - name: petId
        in: path
        description: ID of pet that needs to be updated
        required: true
        schema:
          type: string
    requestBody:
      content:
        'application/x-www-form-urlencoded':
          schema:
            type: object
            properties:
              name:
                description: Updated name of the pet
                type: string
              status:
                description: Updated status of the pet
                type: string
            required:
              - status
    responses:
      '200':
        description: Pet updated.
        content:
          'application/json': {}
          'application/xml': {}
      '405':
        description: Method Not Allowed
        content:
          'application/json': {}
          'application/xml': {}
    security:
      - petstore_auth:
          - write:pets
          - read:pets
```


```
_artists#get() :
{

}
```
Сложно оформить как функцию, 