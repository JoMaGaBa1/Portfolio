<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class SessionController extends Controller
{
    public function view(Request $request) //devuelve el usuario asignado a la sesion
    {
        $data = Auth::user();
        return $data;
    }
}
