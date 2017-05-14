-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: teneria
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `catalogo_producto`
--

DROP TABLE IF EXISTS `catalogo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogo_producto` (
  `idCatalogoProducto` int(11) NOT NULL AUTO_INCREMENT,
  `producto` varchar(45) DEFAULT NULL,
  `unidadMedida` varchar(45) DEFAULT NULL,
  `stockActual` double DEFAULT NULL,
  `stockMinimo` double DEFAULT NULL,
  PRIMARY KEY (`idCatalogoProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_producto`
--

LOCK TABLES `catalogo_producto` WRITE;
/*!40000 ALTER TABLE `catalogo_producto` DISABLE KEYS */;
INSERT INTO `catalogo_producto` (`idCatalogoProducto`, `producto`, `unidadMedida`, `stockActual`, `stockMinimo`) VALUES (1,'Bromuro','Libras',100.53,10),(2,'Azucar','Libras',100,10);
/*!40000 ALTER TABLE `catalogo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo_proveedor`
--

DROP TABLE IF EXISTS `catalogo_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogo_proveedor` (
  `idCatalogoProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `proveedor` varchar(45) DEFAULT NULL,
  `propietario` varchar(45) DEFAULT NULL,
  `nit` varchar(17) DEFAULT NULL,
  `dui` varchar(10) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idCatalogoProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_proveedor`
--

LOCK TABLES `catalogo_proveedor` WRITE;
/*!40000 ALTER TABLE `catalogo_proveedor` DISABLE KEYS */;
INSERT INTO `catalogo_proveedor` (`idCatalogoProveedor`, `proveedor`, `propietario`, `nit`, `dui`, `direccion`, `telefono`) VALUES (5,'proveedor modifcado','Proepi','1230-313213-213-2','05403213-2','123131sad','1321-3213'),(6,'proveedor ','propietario ','1111-111111-111-1','11213213-2','fasdfsaf','3121-3132'),(7,'proveedor','Proepi','1230-313213-213-0','05403213-2','123131sad','1321-3213'),(8,'proveedor','Proepi','1230-313213-213-2','05403213-2','123131sad','1321-3213'),(9,'afdssa','jlñkjlñkj','2132-132132-132-1','13213213-2','dsafasdfsd','1321-3213'),(10,'asfsadfasdf','asfsadfsadf','1321-321321-321-3','23132132-1','sdafasdfsad','1321-3213'),(11,'Ramirez\'s Enterprise','Isabel Magaly Ramirez','0000-000000-000-0','04000000-0','Enterprise','2132-1321'),(12,'q','wqer','','','','');
/*!40000 ALTER TABLE `catalogo_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallefactura` (
  `idDetalleFactura` int(11) NOT NULL AUTO_INCREMENT,
  `idFactura` int(11) DEFAULT NULL,
  `idCatalogoProducto` int(11) DEFAULT NULL,
  `idCatalagoProveedor` int(11) DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `precioUnitario` decimal(10,2) DEFAULT NULL,
  `totalCompra` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idDetalleFactura`),
  KEY `idFactura` (`idFactura`),
  KEY `idCatalogoProducto` (`idCatalogoProducto`),
  KEY `detallefactura_ibfk_3_idx` (`idCatalagoProveedor`),
  CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`idFactura`) REFERENCES `factura` (`idFactura`),
  CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`idCatalogoProducto`) REFERENCES `catalogo_producto` (`idCatalogoProducto`),
  CONSTRAINT `detallefactura_ibfk_3` FOREIGN KEY (`idCatalagoProveedor`) REFERENCES `catalogo_proveedor` (`idCatalogoProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallefactura`
--

LOCK TABLES `detallefactura` WRITE;
/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `idFactura` int(11) NOT NULL AUTO_INCREMENT,
  `numeroFactura` varchar(15) DEFAULT NULL,
  `totalVenta` decimal(10,2) DEFAULT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idFactura`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` (`idFactura`, `numeroFactura`, `totalVenta`, `fechaRegistro`) VALUES (1,'dsafasd',16.00,'2017-05-10 06:00:00'),(2,'',16.00,'2017-05-02 06:00:00'),(3,'',16.00,'2017-05-02 06:00:00'),(4,'',518.00,'2017-05-04 06:00:00'),(5,'',28.00,'2017-05-03 06:00:00'),(6,'',292.00,'2017-05-17 06:00:00'),(7,'',NULL,'2017-05-11 06:00:00'),(8,'77',32.00,'2017-05-13 06:00:00'),(9,'',180.00,'2017-05-11 06:00:00'),(10,'',16.00,'2017-05-13 06:00:00'),(11,'',49.00,'2017-05-13 06:00:00'),(12,'',16.00,'2017-05-06 06:00:00'),(13,'',220.00,'2017-05-13 06:00:00');
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `dui` varchar(10) DEFAULT NULL,
  `nit` varchar(17) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`id_persona`, `nombres`, `apellidos`, `sexo`, `dui`, `nit`, `fechaNacimiento`, `direccion`, `telefono`) VALUES (1,'bryan','franco','M',NULL,NULL,'2017-04-15',NULL,NULL),(2,'Bryan','Franco',NULL,'04396442-1','0210-160690-108-7','2017-04-07','11 av Sur entre 19 y 21 cll. ote #13 Barrio San miguelito','7922-5581'),(3,'juan ','perez','F','04396442-1','0210-160690-108-7','2017-04-16','fafasf','5424-2424'),(4,'Bryan','Franco','M','12313213-2','1132-132132-132-1','1975-04-08','11 av Sur entre 19 y 21 cll. ote #13 Barrio San miguelito','7922-5581');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `clave` varchar(50) DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL,
  `estado` varchar(15) DEFAULT 'ACTIVO',
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id_usuario`, `usuario`, `clave`, `tipo`, `estado`) VALUES (1,'bfranco','123','A','1'),(2,'bsfranco','12345',NULL,'1'),(3,'jperez','123',NULL,'1'),(4,'iramirez','123',NULL,'1');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-13 19:08:09
