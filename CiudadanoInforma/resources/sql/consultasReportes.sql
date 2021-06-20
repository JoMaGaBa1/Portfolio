SELECT e.nombre, ROUND((COUNT(i.id)/(SELECT COUNT(*) FROM incidencias)) * 100, 2)
FROM incidencias i
JOIN estados e ON e.id = i.estado_id
GROUP BY e.nombre;

SELECT e.nombre, ROUND((COUNT(i.id)/(SELECT COUNT(*) FROM incidencias)) * 100, 2)
FROM incidencias i
JOIN equipos e ON e.id = i.equipo_id
GROUP BY e.nombre;

SELECT e.nombre, ROUND((COUNT(*)/(SELECT COUNT(*) FROM incidencias WHERE equipo_id = e.id)) * 100, 2)
FROM incidencias i
JOIN equipos e ON e.id = i.equipo_id
WHERE i.estado_id = (SELECT est.id FROM estados est WHERE est.nombre = 'resuelta')
GROUP BY e.nombre