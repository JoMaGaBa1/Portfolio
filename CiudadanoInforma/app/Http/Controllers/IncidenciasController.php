<?php

namespace App\Http\Controllers;

use App\Models\Categoria;
use App\Models\Equipo;
use App\Models\Estado;
use App\Models\Incidencia;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class IncidenciasController extends Controller
{
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    public function index() //prepara la vista con los datos correspondientes
    {
        $incidencias = Incidencia::all();
        return view('incidencias')
            ->with('incidencias', $incidencias);
    }

    public function updateView($id) //prepara la vista para modificar una incidencia
    {
        $incidencia = Incidencia::findOrFail($id);
        return view('incidenciasUpdate')
            ->with('incidencia', $incidencia)
            ->with('estados', Estado::all())
            ->with('categorias', Categoria::all())
            ->with('equipos', Equipo::all());
    }

    public function update(Request $request) //modifica una incidencia
    {
        $incidencia = Incidencia::findOrFail($request->id);
        $incidencia->estado_id = $request->estado;
        $incidencia->categoria_id = $request->categoria;
        $incidencia->equipo_id = $request->equipo;
        $incidencia->save();
        if (!isset($request->comentario) || !strlen($request->comentario) > 0) { //si no se ha puesto comentario, se inserta uno por defecto
            $request->comentario = 'La incidencia ha sido modificada.';
        }
        DB::table('comentarios')->insert([
            'contenido' => $request->comentario,
            'incidencia_id' => $incidencia->id,
            'user_id' => Auth::id(),
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        return redirect('/incidencias?id=' . $incidencia->id . '#' . $incidencia->id);
    }

    public function delete($id) //elimina una incidencia
    {
        Incidencia::destroy($id);
        return redirect('/incidencias');
    }
}
