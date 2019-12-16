<template>

  <mdb-navbar expand="large" dark color="stylish">
    <mdb-navbar-brand>
      MQTT
    </mdb-navbar-brand>
    <mdb-navbar-toggler>
      <mdb-navbar-nav right>
        <!--<mdb-nav-item href="/login" active>Login </mdb-nav-item>-->
        <mdb-dropdown tag="li" class="nav-item">
          <mdb-dropdown-toggle tag="a" navLink  slot="toggle" waves-fixed>Login</mdb-dropdown-toggle>
          <mdb-dropdown-menu>
            <form>
              <header-login> </header-login>
            </form>

          </mdb-dropdown-menu>
        </mdb-dropdown>
      </mdb-navbar-nav>

    </mdb-navbar-toggler>
  </mdb-navbar>
</template>



<script>
  import Login from '@/components/Login.vue';

  export default {
    name: 'NavbarPage',
    components: {

      'header-login':Login
    }
  }
</script>

<style scoped>

</style>