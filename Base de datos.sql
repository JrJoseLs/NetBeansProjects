drop table VentasDetalles;
drop table VentasMaster;
drop table clientes;
drop table Vendedor;
drop table Productos;

--manejar unique en la cedula
create table Clientes
(
	Codigo int primary key identity,
	Cedula varchar(15) not null,
	Nombre varchar(25) not null,
	Direccion varchar(50) not null,
	Genero varchar(1) not null,
	Estado varchar(30) not null,
	CONSTRAINT CHK_GeneroCliente CHECK (Genero =  'F' or Genero = 'M'),
	CONSTRAINT CHK_EstadoCliente CHECK (Estado =  'Activo' or Estado = 'Inactivo')
)

--manejar unique en la cedula
create table Vendedor
(
	Codigo int primary key identity,
	Cedula varchar(15) not null,
	pin varchar(15) not null,
	Nombre varchar(25) not null,
	Direccion varchar(50) not null,
	Genero varchar(1) not null,
	Estado varchar(30) not null,
	Rol varchar(30) not null,
	CONSTRAINT CHK_GeneroVendedor CHECK (Genero =  'F' or Genero = 'M'),
	CONSTRAINT CHK_EstadoVendedor CHECK (Estado =  'Activo' or Estado = 'Inactivo'),
	CONSTRAINT CHK_RolVendedor CHECK (Rol =  'Estandar' or Rol = 'Administrador')
)

INSERT INTO Vendedor(Cedula,Nombre,Direccion,Genero,Estado,Rol,Pin)values('123456','Administrador','Yo soy la pampara','M','Activo', 'Administrador','admin')
INSERT INTO Vendedor(Cedula,Nombre,Direccion,Genero,Estado,Rol,Pin)values('1234567','Estandar','Yo no soy la pampara','M','Activo', 'Estandar','Estan')


--agregar FK a con la tabla vendedor
create table Productos
(
	Codigo int primary key identity,
	Nombre varchar(25) not null,
	precio decimal not null,
	Vendedor int not null,
	FOREIGN KEY (Vendedor) REFERENCES Vendedor(Codigo)

)

--agregar FK a con la tabla vendedor
create table VentasMaster
(
	Codigo int primary key identity,
	Cliente int not null,
	Fecha date not null,
	Vendedor int not null,
	TipoCompra varchar(30) not null,
	Estado varchar(30) not null,
	CONSTRAINT CHK_TipoCompra CHECK (TipoCompra =  'Credito' or TipoCompra = 'Efectivo' or TipoCompra = 'Cheque'),
	CONSTRAINT CHK_Estado CHECK (Estado =  'En Proceso' or Estado = 'Facturada' or Estado = 'Cancelada'),
	FOREIGN KEY (Cliente) REFERENCES Clientes(Codigo),
	FOREIGN KEY (Vendedor) REFERENCES Vendedor(Codigo)
)

--agregar FK a con las tablas vende cliente
create table VentasDetalles
(
	Codigo int primary key identity,
	CodigoMaster int not null,
	producto int not null,
	Cantidad int not null,
	FOREIGN KEY (CodigoMaster) REFERENCES VentasMaster(Codigo),
	FOREIGN KEY (producto) REFERENCES Productos(Codigo),

)