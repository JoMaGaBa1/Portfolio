<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AuthController extends Controller
{
    public function login(Request $request) //loguea a un usuario
    {
        if (!Auth::attempt($request->only('email', 'password'))) { //si los datos no son válidos, devuelve 401
            return response()->json([
                'message' => 'Invalid login details'
            ], 401);
        }

        $user = User::where('email', $request['email'])->firstOrFail();

        $token = $user->createToken('api_token')->plainTextToken;

        return response()->json([
            'access_token' => $token,
            'token_type' => 'Bearer',
        ]);
    }

    public function logout(Request $request) //desloguea a un usuario y elimina el token de esa sesion
    {
        $request->user()->currentAccessToken()->delete();
        return response()->json([
            'message' => 'Succesfully logged out'
        ]);
    }
}
