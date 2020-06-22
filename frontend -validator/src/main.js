import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
import VueMask from 'v-mask';

import {router} from "./routes";

Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(VueMask);

Vue.config.productionTip = false

Vue.http.interceptors.push(function (request) {

    request.headers.set('Authorization', 'Basic YWRtaW46YWRtaW4xMjM=');

});

new Vue({
  router: router,
  render: h => h(App),
}).$mount('#app')
