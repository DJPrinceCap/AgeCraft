package elcon.mods.agecraft.asm;

import java.io.Writer;
import java.util.HashMap;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import cpw.mods.fml.relauncher.IClassTransformer;

public class ACCoreSuperClassTransformer implements IClassTransformer {

	public HashMap<String, String> modifiers = new HashMap<String, String>();
	
	public ACCoreSuperClassTransformer() {
		modifiers.put("sq", "elcon/mods/agecraft/player/EntityPlayerAC");
	}
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if(bytes == null) {
			return null;
		}
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);
		
		System.out.println("[ElConCore] Checking class " + name + " that extends " + classNode.superName);
		
		if(!modifiers.containsKey(classNode.superName)) {
			return bytes;
		}		
		
		System.out.println("[ElConCore] Transfoming class " + name + " that extends " + classNode.superName + " to " + modifiers.get(name));
		
		classNode.superName= modifiers.get(name);
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}
}
