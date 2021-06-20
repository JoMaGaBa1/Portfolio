<?php

namespace App\Http\Controllers;

use App\Models\Estado;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class EstadosController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function index(Request $request) //prepara la vista con los datos correspondientes
    {
        $id = $request->query('id');
        $titulo = 'Estados';
        $tituloTarjeta = 'Estados registrados';
        $elementos = Estado::all();
        $url = 'estados';
        $create = 'estado';
        return view('basic')
            ->with('titulo', $titulo)
            ->with('tituloTarjeta', $tituloTarjeta)
            ->with('elementos', $elementos)
            ->with('url', $url)
            ->with('create', $create)
            ->with('id', $id);
    }

    public function view($id = -1) //revisa si trae un id para saber si esta modificando o creando
    {
        if ($id === -1) {
            $prev = 'Nombre del nuevo estado';
            $tipo = 1;
            $tituloTarjeta = 'Nuevo estado';
        } else { //si esta modificando busca los datos existentes para mostrarlos
            $prev = Estado::findOrFail($id)->nombre;
            $tipo = 2;
            $tituloTarjeta = 'Estado #' . $id;
        }
        $titulo = 'Estados';
        $url = 'estados';
        return view('basicInput')
            ->with('tipo', $tipo)
            ->with('id', $id)
            ->with('titulo', $titulo)
            ->with('tituloTarjeta', $tituloTarjeta)
            ->with('prev', $prev)
            ->with('url', $url);
    }

    public function insert(Request $request) //inserta nueva categoria
    {
        $id = DB::table('estados')->insertGetId([
            'nombre' => $request->nombre,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        return redirect('/estados?id=' . $id . '#' . $id);
    }

    public function update(Request $request) //modifica categoria existente
    {
        $categoria = Estado::findOrFail($request->id);
        $categoria->nombre = $request->nombre;
        $categoria->save();
        return redirect('/estados?id=' . $request->id . '#' . $request->id);
    }

    public function delete($id) //elimina categoria
    {
        Estado::destroy($id);
        return redirect('/estados');
    }
}
