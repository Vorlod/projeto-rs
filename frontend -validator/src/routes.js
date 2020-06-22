import inicial from './components/HelloWorld.vue';
import publicArea from './components/PublicArea.vue';

import VueRouter from 'vue-router';
export const routes = [
  {
    path:'', component:publicArea, meta: {title:'Inicio'},
    children: [
      {path: '/inicio', alias: '', component: inicial, meta: {title: 'Login'}}
    ]
  }
];

export const router = new VueRouter({
  routes: routes,
  mode: 'history'
});