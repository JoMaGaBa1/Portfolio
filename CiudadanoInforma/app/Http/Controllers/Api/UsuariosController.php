<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\UserResource;
use App\Models\User;

class UsuariosController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth:sanctum', 'admin']); //requiere ser administrador
    }

    public function index() //devuelve todos los usuarios
    {
        return response()->json(UserResource::collection(User::all()));
    }

    public function extractWithId($id) //devuelve un usuario concreto
    {
        return response()->json(new UserResource(User::findOrFail($id)));
    }
}
