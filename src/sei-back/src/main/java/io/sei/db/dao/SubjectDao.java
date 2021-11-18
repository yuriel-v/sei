package io.sei.db.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

import io.sei.db.model.Subject;

public class SubjectDao
{
    private static final HashMap<String, Subject> subjects = new HashMap<String, Subject>();

    public SubjectDao() 
    {
        // mocked data, only includes computer science classes
        if (SubjectDao.subjects.isEmpty())
        {
            String[] compsci = {
                // 1
                "AL1", "Algoritmos I",
                "IPG", "Introdução à Programação",
                "ERS", "Ética e Responsabilidade Social",
                "CEX", "Comunicação e Expressão",
                "RLG", "Raciocínio Lógico",
                // 2
                "AL2", "Algoritmos II",
                "PGE", "Programação Estruturada",
                "ARC", "Arquitetura de Computadores",
                "LGM", "Lógica Matemática",
                "CDI", "Cálculo Diferencial e Integral",
                "TES", "Teoria da Engenharia de Software",
                // 3
                "ESD", "Estrutura de Dados",
                "COO", "Conceitos de Orientação a Objetos",
                "CDG", "Circuitos Digitais",
                "MDS", "Matemática Discreta",
                "BD1", "Banco de Dados I",
                "ERQ", "Engenharia de Requisitos",
                "ALN", "Álgebra Linear",
                // 4
                "EST", "Estatística",
                "POO", "Programação Orientada a Objetos",
                "FRD", "Fundamentos de Redes de Computadores",
                "TCP", "Teoria da Computação",
                "BD2", "Banco de Dados II",
                "APS", "Análise e Projeto de Sistemas",
                "CGR", "Computação Gráfica",
                // 5
                "PEC", "Processos Estocásticos",
                "SOP", "Sistemas Operacionais",
                "ARD", "Arquitetura de Redes de Computadores",
                "LFR", "Linguagens Formais",
                "TGR", "Teoria em Grafos",
                "QSW", "Qualidade de Software",
                "MNC", "Métodos Numéricos Computacionais",
                // 6
                "IAR", "Inteligência Artificial",
                "DAW", "Desenvolvimento de Aplicações Web",
                "MDC", "Metodologia Científica",
                "CAL", "Complexidade de Algoritmos",
                "OST", "Otimização de Sistemas",
                "EMP", "Empreendedorismo",
                // 7
                "CBG", "Conceitos Básicos de Gestão",
                "DAM", "Desenvolvimento de Aplicações Móveis",
                "PRI", "Projeto Integrador em Ciência da Computação",
                "CMP", "Compiladores",
                "DAB", "Desenvolvimento de Aplicações com Banco de Dados",
                // 8
                "SIN", "Segurança da Informação",
                "TAA", "Tópicos Avançados em Arquitetura de Computadores",
                "DAD", "Desenvolvimento de Aplicações Distribuídas",
                "TCC", "Trabalho de Conclusão de Curso em Ciência da Computação"
            };

            for (int i = 0; i < compsci.length; i += 2) {
                SubjectDao.subjects.put(
                    new String(compsci[i].getBytes(), StandardCharsets.UTF_8),
                    new Subject(
                        new String(compsci[i].getBytes(), StandardCharsets.UTF_8),
                        new String(compsci[i+1].getBytes(), StandardCharsets.UTF_8)
                    )
                );
            }
        }
    }

    private static void checkNullFields(Subject subject) throws IOException
    {
        for (String field : Arrays.asList(subject.getId(), subject.getName()))
        {
            if (field.isBlank() || field == null) {
                throw new IOException("Null or empty fields are not allowed");
            }
        }
    }

    public void add(Subject subject) throws IOException
    {
        if (this.exists(subject.getId())) {
            throw new IOException("Subject already exists in database");
        }
        else
        {
            SubjectDao.checkNullFields(subject);
            SubjectDao.subjects.put(subject.getId(), subject);
        }
    }

    public void add(String id, String name) throws IOException
    {
        Subject sbj = new Subject(id, name);
        this.add(sbj);
    }

    public Subject findById(String id) {
        return SubjectDao.subjects.get(id);
    }
    
    private boolean exists(String id) {
        return SubjectDao.subjects.containsKey(id);
    }

    public void update(Subject subject) throws IOException
    {
        if (!this.exists(subject.getId())) {
            throw new IOException("Subject does not exist in database");
        }

        if (!(subject.getName().isBlank() || subject.getName() == null)) {
            SubjectDao.subjects.put(subject.getId(), subject);
        }
        else {
            throw new IOException("Subject must not have a null or blank name");
        }
    }

    public void update(String id, String newName) throws IOException
    {
        Subject sbj = new Subject(id, newName);
        this.update(sbj);
    }

    public Subject delete(String id) throws IOException
    {
        if (!this.exists(id)) {
            throw new IOException("Subject does not exist in database");
        }

        return SubjectDao.subjects.remove(id);
    }
}