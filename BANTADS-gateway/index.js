require("dotenv-safe").config();
const jwt = require('jsonwebtoken');
var http = require('http');
const express = require('express')
const httpProxy = require('express-http-proxy');
const app = express();
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var logger = require('morgan');
var helmet = require('helmet');
const cors = require('cors');

app.use(cors({
  origin: 'http://localhost:4200'
}));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))

// parse application/json
app.use(bodyParser.json())

const authServiceProxy = httpProxy('http://host.docker.internal:5000', {
  proxyReqBodyDecorator: function (bodyContent, srcReq) {
    try {
      retBody = {};
      retBody.login = bodyContent.login;
      retBody.senha = bodyContent.senha;
      bodyContent = retBody;
    }
    catch (e) {
      console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  },
  userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode == 200) {
      var str = Buffer.from(proxyResData).toString('utf-8');
      var objBody = JSON.parse(str);
      const id = objBody.id;
      const token = jwt.sign({ id }, process.env.SECRET, {
        expiresIn: 300 // expira em 5 min
      });
      userRes.status(200);
      return { auth: true, token: token, data: objBody };
    }
    else {
      userRes.status(401);
      return { message: 'Login inválido!' };
    }
  }
});

const clientesServiceProxy = httpProxy('http://host.docker.internal:5001');

const clientesPostServiceProxy = httpProxy('http://host.docker.internal:5001', {
  proxyReqBodyDecorator: function (bodyContent, srcReq) {
    try {
      retBody = {};
      retBody.id = bodyContent.id;
      retBody.nome = bodyContent.nome;
      retBody.email = bodyContent.email;
      retBody.cpf = bodyContent.cpf;
      retBody.salario = bodyContent.salario;
      retBody.endereco = {
        id: bodyContent.endereco.id,
        tipo: bodyContent.endereco.tipo,
        logradouro: bodyContent.endereco.logradouro,
        numero: bodyContent.endereco.numero,
        complemento: bodyContent.endereco.complemento,
        cep: bodyContent.endereco.cep,
        cidade: bodyContent.endereco.cidade,
        estado: bodyContent.endereco.estado
      };
      bodyContent = retBody;
    }
    catch (e) {
      console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  }
});

const contasServiceProxy = httpProxy('http://host.docker.internal:5002');

const contasPostServiceProxy = httpProxy('http://host.docker.internal:5002', {
  proxyReqBodyDecorator: function (bodyContent, srcReq) {
    try {
      retBody = {};
      retBody.idCliente = bodyContent.idCliente;
      retBody.dataCriacao = bodyContent.dataCriacao;
      retBody.limite = bodyContent.limite;
      retBody.saldo = bodyContent.saldo;
      retBody.aprovada = bodyContent.aprovada;
      bodyContent = retBody;
    }
    catch (e) {
      console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  }
});

const gerentesGetServiceProxy = httpProxy('http://host.docker.internal:5003');

const gerentesPostServiceProxy = httpProxy('http://host.docker.internal:5003', {
  proxyReqBodyDecorator: function (bodyContent, srcReq) {
    try {
      retBody = {};
      retBody.id = bodyContent.id;
      retBody.nome = bodyContent.nome;
      retBody.email = bodyContent.email;
      retBody.cpf = bodyContent.cpf;
      bodyContent = retBody;
    }
    catch (e) {
      console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  }
});

const gerentesPutServiceProxy = httpProxy('http://host.docker.internal:5003', {
  proxyReqBodyDecorator: function (bodyContent, srcReq) {
    try {
      retBody = {};
      retBody.id = bodyContent.id;
      retBody.nome = bodyContent.nome;
      retBody.email = bodyContent.email;
      retBody.cpf = bodyContent.cpf;
      bodyContent = retBody;
    }
    catch (e) {
      console.log('- ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'PUT';
    return proxyReqOpts;
  }
});

function verifyJWT(req, res, next) {
  const token = req.headers['authorization'].replace('Bearer ', '');
  if (!token) {
    return res.status(401).json({
      auth: false,
      message: 'Token não fornecido.',
    });
  }

  jwt.verify(token, process.env.SECRET, function (err, decoded) {
    if (err) {
      return res.status(500).json({ auth: false, message: 'Falha ao autenticar o token.' });
    }

    req.userId = decoded.id;
    next();
  });
}

// AUTH

app.post('/login', (req, res, next) => {
  authServiceProxy(req, res, next);
});

app.post('/logout', function (req, res) {
  res.json({ auth: false, token: null });
});

// CLIENTES

app.post('/clientes', function (req, res, next) {
  clientesPostServiceProxy(req, res, next);
});

app.get('/clientes', function (req, res, next) {
  clientesServiceProxy(req, res, next);
});

// CONTAS

app.post('/contas', verifyJWT, function (req, res, next) {
  contasPostServiceProxy(req, res, next);
});

app.get('/contas/gerente/:id', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.get('/contas/gerente/:id/top', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.get('/contas/gerente/:id/cliente/:cpf', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.post('/contas/:numero/aprovada', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.post('/contas/:numero/reprovada', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.get('/contas/cliente/:id', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.post('/contas/:numero/deposito', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.post('/contas/:numero/saque', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.post('/contas/:numero/transferencia', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

app.get('/contas/:numero/extrato', verifyJWT, function (req, res, next) {
  contasServiceProxy(req, res, next);
});

// GERENTES

app.post('/gerentes', verifyJWT, (req, res, next) => {
  gerentesPostServiceProxy(req, res, next);
});

app.put('/gerentes/:id', verifyJWT, (req, res, next) => {
  gerentesPutServiceProxy(req, res, next);
});

app.get('/gerentes/:id', verifyJWT, (req, res, next) => {
  gerentesGetServiceProxy(req, res, next);
});

app.get('/gerentes', verifyJWT, (req, res, next) => {
  gerentesGetServiceProxy(req, res, next);
});

app.get('/gerentes/email/:email', verifyJWT, (req, res, next) => {
  gerentesGetServiceProxy(req, res, next);
});

app.delete('/gerentes/:id', verifyJWT, (req, res, next) => {
  gerentesGetServiceProxy(req, res, next);
});

// Configurações do app
app.use(logger('dev'));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

// Cria o servidor na porta 3000
var server = http.createServer(app);
server.listen(3000);