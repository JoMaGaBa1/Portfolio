<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware(['auth', 'admin']);
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index() //prepara la vista con los graficos
    {
        $porcentajeXEstado = $this->construyePorcentajeXEstado();
        $incidenciasXEquipo = $this->construyeIncidenciasXEquipo();
        $incidenciasXEquipoResueltas = $this->construyeIncidenciasXEquipoResueltas();
        return view('home')
            ->with('porcentajeXEstado', $porcentajeXEstado)
            ->with('incidenciasXEquipo', $incidenciasXEquipo)
            ->with('incidenciasXEquipoResueltas', $incidenciasXEquipoResueltas);
    }

    private function construyePorcentajeXEstado()
    {
        $incidencias = DB::table('incidencias')
            ->join('estados', 'estados.id', '=', 'incidencias.estado_id')
            ->select(DB::raw('estados.nombre as name, ROUND((COUNT(incidencias.id)/(SELECT COUNT(*) FROM incidencias)) * 100, 2) as y'))
            ->groupBy('estados.nombre')
            ->get();
        foreach ($incidencias as $inc) {
            $inc->name = ucfirst($inc->name);
            settype($inc->y, "integer");
        }
        return $incidencias;
    }

    private function construyeIncidenciasXEquipo()
    {
        $incidencias = DB::table('incidencias')
            ->join('equipos', 'equipos.id', '=', 'incidencias.equipo_id')
            ->select(DB::raw('equipos.nombre as name, ROUND((COUNT(incidencias.id)/(SELECT COUNT(*) FROM incidencias)) * 100, 2) as y'))
            ->groupBy('equipos.nombre')
            ->get();
        foreach ($incidencias as $inc) {
            $inc->name = ucfirst($inc->name);
            settype($inc->y, "integer");
        }
        return $incidencias;
    }

    private function construyeIncidenciasXEquipoResueltas() //se realiza una consulta sql por simplicidad de codificacion
    {
        $incidencias = DB::select("SELECT e.nombre AS name, ROUND((COUNT(*)/(SELECT COUNT(*) FROM incidencias WHERE equipo_id = e.id)) * 100, 2) AS y FROM incidencias i JOIN equipos e ON e.id = i.equipo_id WHERE i.estado_id = (SELECT est.id FROM estados est WHERE est.nombre = 'resuelta') GROUP BY e.nombre");
        foreach ($incidencias as $inc) {
            $inc->name = ucfirst($inc->name);
            settype($inc->y, "integer");
        }
        return $incidencias;
    }
}
