<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\EstadoResource;
use App\Models\Estado;

class EstadosController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:sanctum');
    }

    public function index() //devuelve todos los estados
    {
        return response()->json(EstadoResource::collection(Estado::all()));
    }

    public function extractWithId($id) //devuelve un estado concreto
    {
        return response()->json(new EstadoResource(Estado::findOrFail($id)));
    }
}
