<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\EquipoResource;
use App\Models\Equipo;

class EquiposController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:sanctum');
    }

    public function index() //devuelve todos los equipos
    {
        return response()->json(EquipoResource::collection(Equipo::all()));
    }

    public function extractWithId($id) //devuelve un equipo concreto
    {
        return response()->json(new EquipoResource(Equipo::findOrFail($id)));
    }
}
