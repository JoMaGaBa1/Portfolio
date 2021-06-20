<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class Prod extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('categorias')->insert([
            'nombre' => 'Inundación',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Robo/Atraco',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Incendio',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Vandalismo',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Maltrato animal',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Parques y jardines',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Agresión',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('categorias')->insert([
            'nombre' => 'Otros',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('equipos')->insert([
            'nombre' => 'Bomberos',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('equipos')->insert([
            'nombre' => 'Policía',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('equipos')->insert([
            'nombre' => 'Sanitarios',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('equipos')->insert([
            'nombre' => 'Parques y jardines',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('equipos')->insert([
            'nombre' => 'Mantenimiento',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('estados')->insert([
            'nombre' => 'Nueva',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('estados')->insert([
            'nombre' => 'En curso',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('estados')->insert([
            'nombre' => 'En revisión',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('estados')->insert([
            'nombre' => 'Resuelta',
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'Animales abandonados',
            'ubicacion' => 'Burguillos',
            'archivo' => null,
            'categoria_id' => 5,
            'equipo_id' => 2,
            'estado_id' => 1,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 1,
            'user_id' => 1,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Se encuentran en un estado de abandono total, sin agua ni comida. Parecen llevar mucho tiempo, probablemente varios meses.',
            'incidencia_id' => 1,
            'user_id' => 1,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencias')->insert([
            'titulo' => 'Árbol caído',
            'ubicacion' => 'Calle San Jacinto',
            'archivo' => null,
            'categoria_id' => 6,
            'equipo_id' => 1,
            'estado_id' => 1,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('incidencia_user')->insert([
            'incidencia_id' => 2,
            'user_id' => 2,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        DB::table('comentarios')->insert([
            'contenido' => 'Árbol de grandes dimensiones caído sobre varios coches. Parece no haber heridos, pero hay cierto pánico en un colegio cercano.',
            'incidencia_id' => 2,
            'user_id' => 2,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
    }
}
