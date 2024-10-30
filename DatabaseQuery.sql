CREATE DATABASE DeliveryApp;
USE DeliveryApp;

CREATE TABLE State (
    idState INT PRIMARY KEY IDENTITY(1,1),
    State VARCHAR(50) NOT NULL
);

CREATE TABLE City (
    idCity INT PRIMARY KEY IDENTITY(1,1),
    City VARCHAR(50) NOT NULL,
    idState INT,
    FOREIGN KEY (idState) REFERENCES State(idState)
);

CREATE TABLE UserType (
    idUserType INT PRIMARY KEY IDENTITY(1,1),
    UserType VARCHAR(15) NOT NULL
);

CREATE TABLE [User] (
    idUser INT PRIMARY KEY IDENTITY(1,1),
    Name VARCHAR(50),
    Email VARCHAR(40),
    Password NVARCHAR(30),
    PhoneNumber VARCHAR(12),
    idUserType INT,
    FOREIGN KEY (idUserType) REFERENCES UserType(idUserType)
);

CREATE TABLE Restaurant (
    idRestaurant INT PRIMARY KEY IDENTITY(1,1),
    NameRestaurant NVARCHAR(50),
    OpenTime TIME,
    CloseTime TIME,
	idUser int,
	FOREIGN KEY (idUser) REFERENCES [User](idUser)
);

CREATE TABLE Address (
    idAddress INT PRIMARY KEY IDENTITY(1,1),
    LineOne VARCHAR(50),
    LineTwo VARCHAR(50),
    ZipCode VARCHAR(10),
    idCity INT,
    idState INT,
    idUser INT,
    idRestaurant INT,
    FOREIGN KEY (idCity) REFERENCES City(idCity),
    FOREIGN KEY (idState) REFERENCES State(idState),
    FOREIGN KEY (idUser) REFERENCES [User](idUser),
    FOREIGN KEY (idRestaurant) REFERENCES Restaurant(idRestaurant)
);

CREATE TABLE Payment (
    idPayment INT PRIMARY KEY IDENTITY(1,1),
    CardName VARCHAR(50),
    CardNumber NVARCHAR(20),
    CVV NVARCHAR(3),
    ExpirationDate DATE,
    idUser INT,
    FOREIGN KEY (idUser) REFERENCES [User](idUser)
);

CREATE TABLE DishType (
    idDishType INT PRIMARY KEY IDENTITY(1,1),
    DishType VARCHAR(50)
);

CREATE TABLE Dish (
    idDish INT PRIMARY KEY IDENTITY(1,1),
    Name VARCHAR(50),
    Description TEXT,
    NormalPrice MONEY,
    OfferPrice MONEY,
    idRestaurant INT,
    idDishType INT,
    FOREIGN KEY (idRestaurant) REFERENCES Restaurant(idRestaurant),
    FOREIGN KEY (idDishType) REFERENCES DishType(idDishType)
);

CREATE TABLE RestaurantDishType (
    idRestaurantDishType INT PRIMARY KEY IDENTITY(1,1),
    idRestaurant INT,
    idDishType INT,
    FOREIGN KEY (idRestaurant) REFERENCES Restaurant(idRestaurant),
    FOREIGN KEY (idDishType) REFERENCES DishType(idDishType)
);

CREATE TABLE [Order] (
    idOrder INT PRIMARY KEY IDENTITY(1,1),
    Date DATE,
    Total MONEY,
    idPayment INT,
    idUser INT,
    FOREIGN KEY (idPayment) REFERENCES Payment(idPayment),
    FOREIGN KEY (idUser) REFERENCES [User](idUser)
);

CREATE TABLE OrderDish (
    idOrderDish INT PRIMARY KEY IDENTITY(1,1),
    UnitPrice MONEY,
    Amount INT,
    idOrder INT,
    idDish INT,
    FOREIGN KEY (idOrder) REFERENCES [Order](idOrder),
    FOREIGN KEY (idDish) REFERENCES Dish(idDish)
);

CREATE TABLE Review (
    idReview INT PRIMARY KEY IDENTITY(1,1),
    Description TEXT,
    Rating INT,
    idDish INT,
    idUser INT,
    FOREIGN KEY (idDish) REFERENCES Dish(idDish),
    FOREIGN KEY (idUser) REFERENCES [User](idUser)
);
