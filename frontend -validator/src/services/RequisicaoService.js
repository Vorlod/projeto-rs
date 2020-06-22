export class RequisicaoService {

  constructor(http) {
    this.http = http;
  }

  requisicao(dados) {
    return this.http.post('http://3.133.79.35:8080/validator/requisicao', dados);
  }

  requisicaoMes(mes) {
    return this.http.get('http://3.133.79.35:8080/validator/requisicao-mes/' + mes);
  }

}
