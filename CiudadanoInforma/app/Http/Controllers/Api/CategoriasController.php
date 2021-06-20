<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\CategoriaResource;
use App\Models\Categoria;

class CategoriasController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:sanctum');
    }

    public function index() //devuelve todas las categorias
    {
        return response()->json(CategoriaResource::collection(Categoria::all()));
    }

    public function extractWithId($id) //devuelve una categoria concreta
    {
        return response()->json(new CategoriaResource(Categoria::findOrFail($id)));
    }
}
