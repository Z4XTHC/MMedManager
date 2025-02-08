/* ROLES CREADOS POR DEFAULT */
INSERT INTO `mmedmanager_db`.`roles` (`nombre`) VALUES ('Administrador');

INSERT INTO `mmedmanager_db`.`roles` (`nombre`) VALUES ('Medico');

INSERT INTO `mmedmanager_db`.`roles` (`nombre`) VALUES ('Recepcionista');

/* USUARIO ADMINISTRADOR POR DEFAULT */
INSERT INTO `mmedmanager_db`.`usuarios` (`id`, `username`, `email`, `password`) VALUES (1, 'admin', 'admin@gmail.com', 'encryptar');

-- Asigna el rol ADMIN al usuario
INSERT INTO `mmedmanager_db`.`usuario_roles` (`usuario_id`, `rol_id`) VALUES (1, 1);
