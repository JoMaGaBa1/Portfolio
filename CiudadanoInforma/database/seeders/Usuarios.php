<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class Usuarios extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $cadena = "admin";
        DB::table('users')->insert([
            'name' => $cadena,
            'email' => $cadena . '@gmail.com',
            'password' => Hash::make($cadena),
            'admin' => true,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        $cadena = "usuario";
        DB::table('users')->insert([
            'name' => $cadena,
            'email' => $cadena . '@gmail.com',
            'password' => Hash::make($cadena),
            'admin' => false,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
    }
}
