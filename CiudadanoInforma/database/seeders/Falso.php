<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class Falso extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $this->call([
            Cebador::class
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'incendio',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('equipos')->insert([
            'nombre' => 'bomberos',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('estados')->insert([
            'nombre' => 'resuelta',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'bache',
            'ubicacion' => 'aqui',
            'categoria_id' => 1,
            'equipo_id' => 1,
            'estado_id' => 51,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 1,
            'user_id' => 51,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 1,
            'user_id' => 52,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'incendio',
            'ubicacion' => 'alli',
            'categoria_id' => 2,
            'equipo_id' => 15,
            'estado_id' => 51,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 2,
            'user_id' => 8,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 2,
            'user_id' => 26,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 2,
            'user_id' => 34,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 2,
            'user_id' => 45,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Esto es un comentario',
            'incidencia_id' => 1,
            'user_id' => 51,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Esto es otro comentario',
            'incidencia_id' => 1,
            'user_id' => 52,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Se ha quemado',
            'incidencia_id' => 2,
            'user_id' => 8,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Todo',
            'incidencia_id' => 2,
            'user_id' => 26,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Todisimo',
            'incidencia_id' => 2,
            'user_id' => 34,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Una locura',
            'incidencia_id' => 2,
            'user_id' => 34,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Y hace puuuuum ya esta aqui la guerraaaa',
            'incidencia_id' => 2,
            'user_id' => 45,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'incendio',
            'ubicacion' => 'alli',
            'categoria_id' => 3,
            'equipo_id' => 15,
            'estado_id' => 44,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'incendio',
            'ubicacion' => 'alli',
            'categoria_id' => 4,
            'equipo_id' => 15,
            'estado_id' => 44,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'incendio',
            'ubicacion' => 'alli',
            'categoria_id' => 5,
            'equipo_id' => 15,
            'estado_id' => 51,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
    }
}
