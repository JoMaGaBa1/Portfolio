<?php

namespace Database\Seeders;

use App\Models\Categoria;
use App\Models\Equipo;
use App\Models\Estado;
use App\Models\User;
use Illuminate\Database\Seeder;

class Cebador extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        User::factory(50)->create();
        Categoria::factory(50)->create();
        Equipo::factory(50)->create();
        Estado::factory(50)->create();
    }
}
