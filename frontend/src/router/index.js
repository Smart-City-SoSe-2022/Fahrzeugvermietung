import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VehicleView from '../views/VehicleView.vue'
import LendVehicleView from '../views/LendVehicleView.vue'
import MyVehicleView from '../views/MyVehicleView.vue'
import NewVehicleView from '../views/NewVehicleView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/fahrzeug/:id',
    name: 'vehicle',
    props: true,
    component: VehicleView
  },
  {
    path: '/vermietete_fahrzeuge',
    name: 'lendVehicles',
    props: true,
    component: LendVehicleView
  },
  {
    path: '/gemietete_fahrzeuge',
    name: 'MyVehicles',
    props: true,
    component: MyVehicleView
  },
  {
    path: '/neues_fahrzeug',
    name: 'NewVehicle',
    props: true,
    component: NewVehicleView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
