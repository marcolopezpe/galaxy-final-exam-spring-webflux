db = db.getSiblingDB('db_licenciapp');

db.createCollection('usuarios');
db.createCollection('clientes');

db.usuarios.insertMany([
    {
        nombres: 'MARCO',
        apellidos: 'LOPEZ',
        nombre_usuario: 'ADMIN',
        contrasena: '$2a$10$NAy/Mtw9MvWDwVqSA7lF2u2ARSPctp8Gp.8WPIfVqFdu8RIopjws.',
        email: 'MARCOLOPEPE@OUTLOOK.COM',
        roles: ['ADMIN'],
        estado: "Activo"
    }
]);

db.clientes.insertMany([
    {
        apellidos: 'CAMPOS GONZALES',
        nombres: 'FELIX SEGUNDO',
        numero_documento: '42091234',
        email: 'FCAMPOSG@GMAIL.COM',
        licencia: {
            clase_categoria: 'A IIa',
            numero_licencia: 'Q42091234',
            tipo_licencia: 'FISICA',
            fecha_expedicion: '19/09/2006',
            restricciones: 'CON LENTES',
            vigente_hasta: '08/01/2025',
            centro_emision: 'MTC - LINCE'
        },
        puntos_acumulados: 0,
        record_numero: '342321542024',
        tramites: [
            {
                numero_licencia: 'Q42091234',
                clase_categoria: 'A IIa',
                tramite: 'Revalidación',
                fecha_expedicion: '19/09/2006',
                fecha_emision: '08/01/2020',
                fecha_revalidacion: '08/01/2025'
            },
            {
                numero_licencia: 'Q42091234',
                clase_categoria: 'A IIa',
                tramite: 'Revalidación',
                fecha_expedicion: '19/09/2006',
                fecha_emision: '02/01/2017',
                fecha_revalidacion: '02/01/2020'
            },
            {
                numero_licencia: 'Q42091234',
                clase_categoria: 'A IIa',
                tramite: 'Revalidación',
                fecha_expedicion: '19/09/2006',
                fecha_emision: '11/01/2014',
                fecha_revalidacion: '11/01/2017'
            }
        ]
    }
]);

