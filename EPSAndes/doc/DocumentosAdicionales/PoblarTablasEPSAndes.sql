--EPS
insert into EPS(NOMBRE) values('EPS Andes');

--IPS
insert into IPS (NOMBRE, LOCALIZACION, ID_EPS) values ('OPTICAS HORUS', -22.2169379, 'EPS Andes');
insert into IPS (NOMBRE, LOCALIZACION, ID_EPS) values ('PMA MARLY', -18.7679039, 'EPS Andes');
--ROL
insert into ROL(NOMBRE) values ('Afiliado');
insert into ROL(NOMBRE) values ('Medico');
insert into ROL(NOMBRE) values ('RecepcionistaIPS');
insert into ROL(NOMBRE) values ('GerenteEPS');
insert into ROL(NOMBRE) values ('AdminDatosEPS');

--A�ADIENDO USUARIOS :(
--RECEPCIONISTA
insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('eastupenas0@joomla.org', 'Eunice Astupenas', '2269802249', 'RecepcionistaIPS', 'C.C');
insert into  RECEPCIONISTA_IPS (ID_IPS, IDENTIFICACION) values ('PMA MARLY','2269802249');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('msynnot1@youtube.com', 'Merrill Synnot', '6654847202', 'RecepcionistaIPS', 'C.C');
insert into  RECEPCIONISTA_IPS (ID_IPS, IDENTIFICACION) values ('OPTICAS HORUS','6654847202');

--AFILIADO
insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('thaverty0@state.gov', 'Tobie Haverty', '4766993140', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '4766993140', '9/21/2016');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('lsavoury1@discovery.com', 'Lib Savoury', '4392220242', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '4392220242', '3/13/2014');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('dharrower2@plala.or.jp', 'Dahlia Harrower', '1401298548', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '1401298548', '7/12/2005');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('twetherhead3@cpanel.net', 'Tawnya Wetherhead', '9099875607', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '9099875607', '5/8/2014');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('kcotillard4@people.com.cn', 'Kirsten Cotillard', '1786935622', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '1786935622', '7/30/2009');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('jkillingback5@nationalgeographic.com', 'Juanita Killingback', '7927052034', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '7927052034', '9/18/2002');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mmckerlie6@nhs.uk', 'Micky McKerlie', '8915105602', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '8915105602', '5/9/2010');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('gblenkinship7@typepad.com', 'Gery Blenkinship', '4463190417', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '4463190417', '8/19/2001');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('lioannou8@icq.com', 'Lyn Ioannou', '1305905295', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '1305905295', '8/12/2001');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('bjochen9@usgs.gov', 'Bethany Jochen', '2281301816', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '2281301816', '4/21/2006');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('jrennoxa@smh.com.au', 'Jermaine Rennox', '3186337494', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '3186337494', '9/14/2011');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('lbunhillb@nhs.uk', 'Lorry Bunhill', '6169298267', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '6169298267', '9/12/2007');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('gdurmanc@nps.gov', 'Gabbie Durman', '3910202861', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '3910202861', '3/23/2003');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('btopd@pinterest.com', 'Beniamino Top', '9569578546', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '9569578546', '4/18/2013');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('bfruchtere@flickr.com', 'Barrett Fruchter', '3617912686', 'Afiliado', 'T.I');
insert into  AFILIADO (EPS, IDENTIFICACION, FECHA_NACIMIENTO) values ('EPS Andes', '3617912686', '2/15/2004');

--ADMINDATOSEPS
insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('ftuttle0@abc.net.au', 'Filmore Tuttle', '9850442623', 'AdminDatosEPS', 'C.E');
insert into ADMINDATOSEPS (ID_EPS, IDENTIFICACION) values ('EPS Andes', '9850442623');

--GERENTEEPS
insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('hde0@kickstarter.com', 'Hedwiga de Bullion', '3176112004', 'GerenteEPS', 'C.C');
insert into GERENTE_EPS (ID_EPS, IDENTIFICACION) values ('EPS Andes', '3176112004');

--MEDICOS
insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('cmactrustam0@cocolog-nifty.com', 'Clayton MacTrustam', '5771648304', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '5771648304', '7734192119');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('lgodwyn1@parallels.com', 'Laurena Godwyn', '4409339694', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '4409339694', '0630534403');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mstorrah2@sciencedirect.com', 'Miner Storrah', '6232777254', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '6232777254', '8391103338');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('cveasey3@rediff.com', 'Chanda Veasey', '9846018356', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '9846018356', '6629607940');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mreichert4@cocolog-nifty.com', 'Michele Reichert', '7472567864', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '7472567864', '0566922518');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('sflanaghan5@moonfruit.com', 'Suki Flanaghan', '7333553484', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '7333553484', '9986120632');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('gboshers6@mapy.cz', 'Gayle Boshers', '1134991693', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '1134991693', '4655879087');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('jblazej7@telegraph.co.uk', 'Jeniece Blazej', '0032965177', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '0032965177', '9564454390');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mdugall8@trellian.com', 'Moe Dugall', '6180804597', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '6180804597', '3292461168');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('csinkinson9@com.com', 'Cletus Sinkinson', '2732656244', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '2732656244', '1526694874');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mfilyukova@thetimes.co.uk', 'Marve Filyukov', '6851195593', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Cardi�logo', '6851195593', '5735704865');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('jcumob@google.fr', 'Johnna Cumo', '8121232584', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '8121232584', '6835106232');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('ahuntressc@fema.gov', 'Arlin Huntress', '6342869890', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Opt�metra', '6342869890', '3966740109');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('gyallopd@miibeian.gov.cn', 'Greer Yallop', '7659196103', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Pediatra', '7659196103', '8293659376');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('pmcviee@hao123.com', 'Pyotr McVie', '7532632250', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '7532632250', '8590525502');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('icowenf@weibo.com', 'Inessa Cowen', '0651020713', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Cardi�logo', '0651020713', '2741732882');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('fgrenfellg@oaic.gov.au', 'Farra Grenfell', '2231674711', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '2231674711', '6524648153');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('dschulkeh@imgur.com', 'Desmond Schulke', '9421620964', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '9421620964', '8243209813');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('adurandi@nps.gov', 'Adena Durand', '3724179320', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '3724179320', '2539000143');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('hfairecloughj@google.it', 'Hans Faireclough', '0685783505', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Cardi�logo', '0685783505', '8270598964');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mvondruskak@home.pl', 'Mile Vondruska', '6253899220', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '6253899220', '0875902892');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('jfiddymentl@unc.edu', 'Jocelyn Fiddyment', '9046787033', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '9046787033', '1254945599');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('fwasteneym@last.fm', 'Felita Wasteney', '4748802805', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '4748802805', '9288370200');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('dsoarsn@spotify.com', 'Derrick Soars', '2586752375', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Cardi�logo', '2586752375', '5815806487');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('ajirano@devhub.com', 'Andeee Jiran', '8073802936', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Neur�logo', '8073802936', '4346433709');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mconnp@accuweather.com', 'Man Conn', '7092159742', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '7092159742', '2292919660');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('mmargrieq@topsy.com', 'Mikkel Margrie', '3410324063', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '3410324063', '6049116983');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('loglevier@bbb.org', 'Liva Oglevie', '5001980995', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '5001980995', '7388115132');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('bbrahams@a8.net', 'Brita Braham', '4553891079', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '4553891079', '5523492823');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('cwombwellt@mtv.com', 'Caitrin Wombwell', '6694397042', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Otorrinolaring�logo', '6694397042', '8729920539');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('jyeendu@irs.gov', 'Job Yeend', '3455783020', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '3455783020', '1256143317');

insert into USUARIO (EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values ('roldknowv@irs.gov', 'Rufe Oldknow', '7447790485', 'Medico', 'C.C');
insert into MEDICO (ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values ('Anestesi�logo', '7447790485', '2686607102');

--SERVICIOS
insert into SERVICIO (NOMBRE, TIPO) values ('Consulta dolor cardiaco', 'Consulta de urgencias');
insert into SERVICIO (NOMBRE, TIPO) values ('Consulta malestar general', 'Consulta con medico');
insert into SERVICIO (NOMBRE, TIPO) values ('Remisi�n cardi�logo', 'Remision con un especialista');
insert into SERVICIO (NOMBRE, TIPO) values ('Cirug�a de coraz�n', 'Procedimiento medico especializado');
insert into SERVICIO (NOMBRE, TIPO) values ('Consulta progreso de diabetes','Consulta de control');
insert into SERVICIO (NOMBRE, TIPO) values ('Examen de sangre', 'Examen diagnostico');
insert into SERVICIO (NOMBRE, TIPO) values ('Terapia post traum�tica', 'Terapia');
insert into SERVICIO (NOMBRE, TIPO) values ('Hospitalizaci�n por infarto', 'Hospitalizacion');

--PRESTAN
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('06', '10:00:30', '06-12-18 10:00:30', 'Consulta dolor cardiaco', 'OPTICAS HORUS', '16');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('00', '16:30:20', '28-11-19 16:30:20', 'Consulta malestar general', 'OPTICAS HORUS', '09');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('40', '23:48:10', '22-10-19 23:48:10', 'Remisi�n cardi�logo', 'OPTICAS HORUS', '61');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('36', '4:08:23', '08-12-18 4:08:23', 'Cirug�a de coraz�n', 'OPTICAS HORUS', '97');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('93', '5:45:25', '20-05-19 5:45:25', 'Consulta progreso de diabetes', 'PMA MARLY', '66');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('56', '14:32:00', '30-08-19 14:32:00', 'Terapia post traum�tica', 'PMA MARLY', '70');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('94', '6:42:04', '21-06-19 6:42:04', 'Examen de sangre', 'PMA MARLY', '42');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('06', '11:02:24', '10-08-19 11:02:24', 'Hospitalizaci�n por infarto', 'PMA MARLY', '65');

insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('80', '8:04:30', '08-08-18 8:04:30', 'Cirug�a de coraz�n', 'PMA MARLY', '5');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('50', '4:55:21', '20-12-19 4:55:21', 'Consulta progreso de diabetes', 'OPTICAS HORUS', '4');
insert into PRESTAN (DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values ('20', '10:30:15', '30-12-19 10:30:15', 'Terapia post traum�tica', 'OPTICAS HORUS', '10');

--OFRECEN
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('5771648304','Hospitalizaci�n por infarto');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('4409339694','Hospitalizaci�n por infarto');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('6232777254','Hospitalizaci�n por infarto');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('9846018356','Hospitalizaci�n por infarto');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('7472567864','Terapia post traum�tica');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('7333553484','Terapia post traum�tica');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('1134991693','Terapia post traum�tica');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('0032965177','Terapia post traum�tica');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('6180804597','Examen de sangre');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('2732656244','Examen de sangre');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('6851195593','Examen de sangre');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('8121232584','Examen de sangre');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('6342869890','Consulta dolor cardiaco');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('7659196103','Consulta dolor cardiaco');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('7532632250','Consulta dolor cardiaco');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('0651020713','Consulta dolor cardiaco');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('2231674711','Consulta malestar general');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('9421620964','Consulta malestar general');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('3724179320','Consulta malestar general');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('0685783505','Consulta malestar general');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('6253899220','Remisi�n cardi�logo');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('9046787033','Remisi�n cardi�logo');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('4748802805','Remisi�n cardi�logo');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('2586752375','Remisi�n cardi�logo');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('8073802936','Cirug�a de coraz�n');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('7092159742','Cirug�a de coraz�n');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('3410324063','Cirug�a de coraz�n');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('5001980995','Cirug�a de coraz�n');

insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('4553891079','Consulta progreso de diabetes');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('6694397042','Consulta progreso de diabetes');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('3455783020','Consulta progreso de diabetes');
insert into OFRECEN(ID_MEDICO,ID_SERVICIO) values ('7447790485','Consulta progreso de diabetes');

--TRABAJAN
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('5771648304','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('4409339694','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('6232777254','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('9846018356','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('7472567864','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('7333553484','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('1134991693','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('0032965177','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('6180804597','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('2732656244','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('6851195593','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('8121232584','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('6342869890','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('7659196103','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('7532632250','OPTICAS HORUS');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('0651020713','OPTICAS HORUS');

insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('2231674711','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('9421620964','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('3724179320','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('0685783505','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('6253899220','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('9046787033','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('4748802805','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('2586752375','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('8073802936','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('7092159742','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('3410324063','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('5001980995','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('4553891079','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('6694397042','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('3455783020','PMA MARLY');
insert into TRABAJAN(ID_MEDICO,ID_IPS) values ('7447790485','PMA MARLY');
