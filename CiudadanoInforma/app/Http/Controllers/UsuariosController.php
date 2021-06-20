<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;

class UsuariosController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function index(Request $request) //prepara la vista con los datos correspondientes
    {
        $id = $request->query('id');
        $usuarios = User::all();
        return view('usuarios')
            ->with('usuarios', $usuarios)
            ->with('id', $id);
    }

    public function delete($id) //elimina un usuario existente
    {
        User::destroy($id);
        return redirect('/usuarios');
    }
}
