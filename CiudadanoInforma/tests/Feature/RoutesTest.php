<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class RoutesTest extends TestCase
{
    /**  @test */
    public function loginViewTest()
    {
        $response = $this->get('/login');

        $response->assertStatus(200);
    }

    /**  @test */
    public function notLoggedInHomeTest()
    {
        $response = $this->get('/home');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInIncidenciasTest()
    {
        $response = $this->get('/incidencias');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInUsuariosTest()
    {
        $response = $this->get('/usuarios');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInComentariosTest()
    {
        $response = $this->get('/comentarios');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInCategoriasTest()
    {
        $response = $this->get('/categorias');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInEquiposTest()
    {
        $response = $this->get('/equipos');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInEstadosTest()
    {
        $response = $this->get('/estados');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInApiUserTest()
    {
        $response = $this->get('/api/user');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInApiListUsersTest()
    {
        $response = $this->get('/api/usuarios');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInApiListIncidenciasTest()
    {
        $response = $this->get('/api/incidencias');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInApiListCategoriasTest()
    {
        $response = $this->get('/api/categorias');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInApiListEquiposTest()
    {
        $response = $this->get('/api/equipos');

        $response->assertStatus(302);
    }

    /**  @test */
    public function notLoggedInApiListEstadosTest()
    {
        $response = $this->get('/api/estados');

        $response->assertStatus(302);
    }
}
