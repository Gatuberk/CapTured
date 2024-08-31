import { Entity, PrimaryGeneratedColumn, Column } from "typeorm"

@Entity('user')
export class UsersEntity{

    @PrimaryGeneratedColumn()
    id_usr: number;

    @Column()
    usr_nombres: string;

    @Column()
    usr_apellidos: string;

    @Column()
    usr_telefono: string;

    @Column()
    usr_direccion: string;

    @Column()
    usr_identificacion: string;

    @Column()
    usr_username: string;

    @Column()
    usr_password: string;

    @Column()
    usr_active: boolean;

}