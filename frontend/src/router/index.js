import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VehicleView from '../views/VehicleView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/fahrzeug/:id',
    name: 'vehicle',
    props: true,
    component: VehicleView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
