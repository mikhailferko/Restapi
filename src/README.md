# REST API

Для запуска проекта скачать и установить [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

Чтобы запустить проект пропишите в консоли команду:

    mvnw spring-boot:run

Чтобы остановить выполнение программы нажмите

    ctrl + c

Также запустить проект можно с помощью команды:

    mvnw spring-boot:start

При таком запуске, остановить с помощью команды:

    mvnw spring-boot:stop

Запустить выполнение интеграционных тестов можно 
с помощью команды:

    mvnw verify

##ТЗ

Все описанные возвращаемые типы данных находятся в 
параметре _data_. 
В случае ошибки возвращается параметр _error_.

Например, в случае, если запрос корректно отработает, 
бэк возвращает в body ответа:

    {
    
        “data”:{
        
        //то, что в параметре out
        
        }
    
    }

А в случае ошибки возвращает

    {
    
        “error”:”текст ошибки”
    
    }

Везде, где не написан метод, использовать POST

__1. api/organization/list__

    In (фильтр):
    
    {
    
        “name”:””, //обязательный параметр
        
        “inn”:””,
        
        “isActive”:””
    
    }
    
    Out:
        
    [
        
        {
        
            “id”:””,
            
            “name”:””,
            
            “isActive”:”true”
        
        },
        
        ...
        
    ]

__2. api/organization/{id}__


method:GET
    
    Out: 
    
       {
    
           “id”:””,
        
           “name”:””,
        
           “fullName”:””,
        
           “inn”:””,
        
           “kpp”:””,
        
           “address”:””,
        
           “phone”,””,
        
           “isActive”:”true”
    
       }

__3. api/organization/update__

    In:
    
       {
    
           “id”:””, //обязательный параметр
        
           “name”:””, //обязательный параметр
        
           “fullName”:””, //обязательный параметр
        
           “inn”:””, //обязательный параметр
        
           “kpp”:””,  //обязательный параметр
        
           “address”:””, //обязательный параметр
        
           “phone”,””,
        
           “isActive”:”true”
    
       }
    
    Out:
            
        {
            
            “result”:”success”
            
        }

__4. api/organization/save__

    In:
    
       {
    
           “name”:””, //обязательный параметр
        
           “fullName”:””, //обязательный параметр
        
           “inn”:””, //обязательный параметр
        
           “kpp”:””, //обязательный параметр
        
           “address”:””, //обязательный параметр
        
           “phone”,””,
        
           “isActive”:”true”
    
       }
    
    Out:
            
        {
            
            “result”:”success”
            
        }

__5. api/office/list__

    In (фильтр):
    
       {
    
           “orgId”:””, //обязательный параметр
        
           “name”:””,
        
           “phone”:””,
        
           “isActive”
    
       }
    
    Out:
            
    [
            
        {
            
            “id”:””,
            
            “name”:””,
            
            “isActive”:”true”
            
        },
            
        ...
            
    ]

__6. api/office/{id}__

method:GET
    
    Out:
    
       {
    
           “id”:””,
        
           “name”:””,
        
           “address”:””,
        
           “phone”,””,
        
           “isActive”:”true”
    
       }

__7. api/office/update__

    In:
    
       {
    
           “id”:””, //обязательный параметр
        
           “name”:””, //обязательный параметр
        
           “address”:””, //обязательный параметр
        
           “phone”,””,
        
           “isActive”:”true” //пример
    
       }
    
    Out:
            
        {
            
            “result”:”success”
            
        }

__8. api/office/save__

    In:
    
       {
    
           “orgId”:””, //обязательный параметр
        
           “name”:””,
        
           “address”:””,
        
           “phone”,””,
        
           “isActive”:”true”
    
       }
    
    Out:
            
        {
            
            “result”:”success”
            
        }

__9. api/user/list__

    In (фильтр):
    
       {
    
           “officeId”:””, //обязательный параметр
        
           “firstName”:””,
        
           “lastName”:””,
        
           “middleName”:””,
        
           “position”,””,
        
           “docCode”:””,
        
           “citizenshipCode”:””
    
       }
    
    Out:
    
        {
        
           “id”:””,
        
           “firstName”:””,
        
           “secondName”:””,
        
           “middleName”:””,
        
           “position”:””
    
        }

__10. api/user/{id}__

method:GET

    Out:

        {
    
            “id”:””,
    
            “firstName”:””,
    
            “secondName”:””,
    
            “middleName”:””,
    
            “position”:””
    
            “phone”,””,
    
            “docName”:””,
    
            “docNumber”:””,
    
            “docDate”:””,
    
            “citizenshipName”:””,
    
            “citizenshipCode”:””,
    
            “isIdentified”:”true”
    
        }

__11. api/user/update__

    In:

        {
    
            “id”:””, //обязательный параметр
    
            “officeId”:””,
    
            “firstName”:””, //обязательный параметр
    
            “secondName”:””,
    
            “middleName”:””,
    
            “position”:”” //обязательный параметр
    
            “phone”,””,
    
            “docName”:””,
    
            “docNumber”:””,
    
            “docDate”:””,
    
            “citizenshipCode”:””,
    
            “isIdentified”:”true” //пример
    
        }
    
    Out:

        {
    
            “result”:”success”
    
        }

__12. api/user/save__

    In:

        {
    
            “officeId”:””, //обязательный параметр
    
            “firstName”:””, //обязательный параметр
    
            “secondName”:””,
    
            “middleName”:””,
    
            “position”:”” //обязательный параметр
    
            “phone”,””,
    
            “docCode”:””,
    
            “docName”:””,
    
            “docNumber”:””,
    
            “docDate”:””,
    
            “citizenshipCode”:””,
    
            “isIdentified”:”true” //пример
    
        }

__Справочники:__

__api/docs__

    [
    
        {
        
            “name”:“Паспорт гражданина РФ”,
            
            “code”:”21”
        
        },
        
        ...
    
    ]

[Виды документов, удостоверяющих личность 
физического лица](https://www.regberry.ru/zakony/vidy-dokumentov-udostoveryayushchih-lichnost-fizicheskogo-lica)

__api/countries__

    [

        {
    
            “name”:“Российская Федерация”,
        
            “code”:”643”
    
        },
    
        ...

    ]

