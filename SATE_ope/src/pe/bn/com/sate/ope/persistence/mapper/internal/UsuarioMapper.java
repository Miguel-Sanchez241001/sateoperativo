package pe.bn.com.sate.ope.persistence.mapper.internal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pe.bn.com.sate.ope.transversal.dto.sate.Usuario;

public interface UsuarioMapper {

    @Select("SELECT * FROM BN_SATE.BNSATE02_USUARIO WHERE B02_TIPO_DOCUMENTO = #{tipoDocumento} AND B02_NUM_DOCUMENTO = #{numDocumento}")
    @ResultMap("mapUsuario")
    public Usuario buscarUsuario(@Param("tipoDocumento") String tipoDocumento,
                                 @Param("numDocumento") String numDocumento);

    @Select("SELECT * FROM BN_SATE.BNSATE02_USUARIO WHERE B02_TIPO_DOCUMENTO = #{tipoDocumento} AND B02_NUM_DOCUMENTO = #{numDocumento} AND B02_PERFIL_USUARIO != '1'")
    @ResultMap("mapUsuario")
    public Usuario buscarUsuarioSinRepresentante(@Param("tipoDocumento") String tipoDocumento,
                                                 @Param("numDocumento") String numDocumento);

    @Insert("INSERT INTO BN_SATE.BNSATE02_USUARIO(" +
            "B02_TIPO_DOCUMENTO," +
            "B02_NUM_DOCUMENTO," +
            "B02_NOMBRES," +
            "B02_APPATERNO," +
            "B02_APMATERNO," +
            "B02_CORREO," +
            "B02_TELEFONO_MOVIL," +
            "B02_PERFIL_USUARIO," +
            "B02_REP_CREADOR," +
            "B02_ESTADO," +
            "B02_FLAG_CAMBIO_CLAVE) " +
            "VALUES (" +
            "#{tipoDocumento}," +
            "#{numeroDocumento}," +
            "#{nombres}," +
            "#{apPaterno}," +
            "#{apMaterno}," +
            "#{correoLaboral}," +
            "#{telefonoMovil}," +
            "#{usuarioPerfil}," +
            "#{representanteCreador}," +
            "#{estado}," +
            "#{flagCambioClave})")
    public void registrarUsuario(Usuario usuario);

    @Select("SELECT * FROM BN_SATE.BNSATE02_USUARIO usu " +
            "JOIN " +
            "(SELECT repempre.B00_ID_EMP, repempre.B02_REP, MAX(repempre.B03_FEC_REGISTRO) FROM BN_SATE.BNSATE03_REP_EMP repempre " +
            "GROUP BY repempre.B02_REP, repempre.B00_ID_EMP) rem " +
            "ON " +
            "usu.B02_REP_CREADOR = rem.B02_REP " +
            "JOIN BN_SATE.BNSATE00_EMPRESA emp " +
            "ON rem.B00_ID_EMP = emp.B00_ID_EMP " +
            "WHERE " +
            "emp.B00_NUM_RUC = #{ruc} AND usu.B02_NUM_DOCUMENTO = #{numeroDocumento}")
    @ResultMap("mapUsuario")
    public Usuario existeUsuarioEmpresa(@Param("numeroDocumento") String numDocumento,
                                        @Param("ruc") String ruc);

    @Select("SELECT * FROM BN_SATE.BNSATE00_EMPRESA emp JOIN BN_SATE.BNSATE02_USUARIO rep ON emp.B02_REP = rep.B02_REP WHERE emp.B00_NUM_RUC = #{ruc} AND rep.B02_NUM_DOCUMENTO = #{numeroDocumento}")
    @ResultMap("mapUsuario")
    public Usuario existeRepresentanteEmpresa(@Param("numeroDocumento") String numDocumento,
                                              @Param("ruc") String ruc);

    @Update("UPDATE BN_SATE.BNSATE02_USUARIO SET B02_ESTADO = #{estado}, B02_PERFIL_USUARIO = #{usuarioPerfil}, B02_FLAG_CAMBIO_CLAVE = #{flagCambioClave}, B02_CORREO = #{correoLaboral}, B02_TELEFONO_MOVIL = #{telefonoMovil} WHERE B02_REP = #{id}")
    public void actualizarUsuario(Usuario usuario);

}
